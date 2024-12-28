package fmi.wsp.carmanagement.common;

import fmi.wsp.carmanagement.car.CarEntity;
import fmi.wsp.carmanagement.car.DTO.CarRequest;
import fmi.wsp.carmanagement.car.DTO.CarResponse;
import fmi.wsp.carmanagement.car.DTO.CarUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CarMapper {

    private final GarageMapper garageMapper;

    public CarResponse toCarResponse(CarEntity carEntity) {
        return CarResponse.builder()
                .id(carEntity.getId())
                .make(carEntity.getMake())
                .model(carEntity.getModel())
                .productionYear(carEntity.getProductionYear())
                .licensePlate(carEntity.getLicensePlate())
                .garages(carEntity.getGarages().stream().map(garageMapper::toGarageResponse).collect(Collectors.toList()))
                .build();
    }

    public CarEntity toCarEntity(CarRequest carRequest) {
        return CarEntity.builder()
                .make(carRequest.make())
                .model(carRequest.model())
                .productionYear(carRequest.productionYear())
                .licensePlate(carRequest.licensePlate())
                .build();
    }

    public CarEntity toCarEntity(CarEntity carToUpdate, CarUpdateRequest request) {
        carToUpdate.setMake(request.make());
        carToUpdate.setModel(request.model());
        carToUpdate.setProductionYear(request.productionYear());
        carToUpdate.setLicensePlate(request.licensePlate());
        return carToUpdate;
    }
}
