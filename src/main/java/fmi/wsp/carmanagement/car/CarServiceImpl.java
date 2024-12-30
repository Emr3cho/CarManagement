package fmi.wsp.carmanagement.car;

import fmi.wsp.carmanagement.car.DTO.CarRequest;
import fmi.wsp.carmanagement.car.DTO.CarResponse;
import fmi.wsp.carmanagement.car.DTO.CarUpdateRequest;
import fmi.wsp.carmanagement.common.CarMapper;
import fmi.wsp.carmanagement.common.ResourceNotFoundException;
import fmi.wsp.carmanagement.garage.GarageEntity;
import fmi.wsp.carmanagement.garage.GarageService;
import fmi.wsp.carmanagement.maintenance.MaintenanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final GarageService garageService;

    @Override
    public CarResponse fetchCarById(long carId) {
        return carMapper.toCarResponse(carRepository.findById(carId).get());
    }

    @Override
    public CarResponse updateCar(long carId, CarUpdateRequest request) {
        CarEntity carToUpdate = carRepository.findById(carId).get();
        if (request.garageIds() != null && !request.garageIds().isEmpty()) {
            List<GarageEntity> newGarages = garageService.findSpecificGarages(request.garageIds());

            for (GarageEntity oldGarage : carToUpdate.getGarages()) {
                oldGarage.getCars().remove(carToUpdate);
            }
            carToUpdate.getGarages().clear();

            for (GarageEntity newGarage : newGarages) {
                if (!newGarage.getCars().contains(carToUpdate)) {
                    newGarage.getCars().add(carToUpdate);
                }
            }
            carToUpdate.setGarages(newGarages);
        }

        carToUpdate = carMapper.toCarEntity(carToUpdate, request);

        return carMapper.toCarResponse(carRepository.save(carToUpdate));
    }

    @Override
    public CarResponse createCar(CarRequest carRequest) {
        CarEntity carToSave = carMapper.toCarEntity(carRequest);
        List<GarageEntity> garages = carRequest.garageIds().isEmpty()
                ? new ArrayList<>()
                : garageService.findSpecificGarages(carRequest.garageIds());

        carToSave.setGarages(garages);

        for (GarageEntity garage : garages) {
            if (!garage.getCars().contains(carToSave)) {
                garage.getCars().add(carToSave);
            }
        }

        return carMapper.toCarResponse(carRepository.save(carToSave));
    }

    @Override
    @Transactional
    public void deleteCar(long carId) {
        CarEntity carToDelete = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Car with id %s is not available!", carId)
        ));

        List<GarageEntity> garages = carToDelete.getGarages();
        for (GarageEntity garage : garages) {
            garage.getCars().remove(carToDelete);
        }

        List<MaintenanceEntity> maintenanceEntities = carToDelete.getMaintenances();
        for (MaintenanceEntity maintenance : maintenanceEntities) {
            maintenance.setCar(null);
        }

        carRepository.delete(carToDelete);
    }

    @Override
    public List<CarResponse> getAllCarsByCriteria(String carMake, long garageId, int fromYear, int toYear) {
        Specification<CarEntity> spec = CarSpecification.filterCars(carMake, garageId, fromYear, toYear);
        List<CarEntity> carEntities = carRepository.findAll(spec);
        return carEntities.stream().map(carMapper::toCarResponse).toList();
    }
}
