package fmi.wsp.carmanagement.garage;

import fmi.wsp.carmanagement.car.CarEntity;
import fmi.wsp.carmanagement.common.GarageMapper;
import fmi.wsp.carmanagement.common.ResourceNotFoundException;
import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.DTO.GarageResponse;
import fmi.wsp.carmanagement.garage.DTO.GarageReportResponse;
import fmi.wsp.carmanagement.maintenance.MaintenanceEntity;
import fmi.wsp.carmanagement.maintenance.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;
    private final GarageMapper garageMapper;
    private final MaintenanceRepository maintenanceRepository;

    @Override
    public GarageResponse fetchGarage(long garageId) {
        return garageMapper.toGarageResponse(garageRepository.findById(garageId).orElseThrow(() -> new ResourceNotFoundException(String.format("Garage with id %s is not available!", garageId))));
    }

    @Override
    public List<GarageEntity> findSpecificGarages(List<Long> garageIds) {
        return garageRepository.findAllByIdIn(garageIds);
    }

    @Override
    public List<GarageResponse> fetchAllGarages(String city) {
        List<GarageEntity> garages;
        if (city == null) {
            garages = garageRepository.findAll();
        }else {
            garages = garageRepository.findAllByNameStartingWithIgnoreCase(city);
        }
        return garages.stream().map(garageMapper::toGarageResponse).collect(Collectors.toList());
    }

    @Override
    public GarageResponse saveGarage(GarageRequest garageRequest) {
        return garageMapper.toGarageResponse(garageRepository.save(garageMapper.ToGarageEntity(garageRequest)));
    }

    @Override
    public GarageResponse updateGarage(GarageRequest garageRequest, long garageId) {
        GarageEntity updatedGarage = garageMapper.ToGarageEntity(garageRequest);
        updatedGarage.setId(garageId);
        return garageMapper.toGarageResponse(garageRepository.save(updatedGarage));
    }

    @Override
    @Transactional
    public void deleteGarage(long garageId) {
        GarageEntity entityToDelete = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Garage with id %s is not available!", garageId)
                ));

        List<CarEntity> cars = entityToDelete.getCars();
        for (CarEntity car : cars) {
            car.getGarages().remove(entityToDelete);
        }

        List<MaintenanceEntity> maintenances = entityToDelete.getMaintenances();
        for (MaintenanceEntity maintenance : maintenances) {
            maintenance.setGarage(null);
        }

        garageRepository.delete(entityToDelete);
    }

    public List<GarageReportResponse> getCapacityReport(Long garageId, LocalDate startDate, LocalDate endDate) {
        GarageEntity garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new IllegalArgumentException("Garage not found"));

        List<Object[]> maintenanceData = maintenanceRepository.findMaintenanceRequestsByDate(garageId, startDate, endDate);

        Map<String, Long> maintenanceMap = maintenanceData.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> (Long) row[1]
                ));

        List<GarageReportResponse> report = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            long requests = maintenanceMap.getOrDefault(date.toString(), 0L);
            long availableCapacity = garage.getCapacity() - requests;

            report.add(new GarageReportResponse(date, requests, availableCapacity));
        }

        return report;
    }

}
