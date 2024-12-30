package fmi.wsp.carmanagement.common;

import fmi.wsp.carmanagement.car.CarEntity;
import fmi.wsp.carmanagement.car.DTO.CarResponse;
import fmi.wsp.carmanagement.garage.DTO.GarageResponse;
import fmi.wsp.carmanagement.garage.GarageEntity;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceResponse;
import fmi.wsp.carmanagement.maintenance.MaintenanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceMapper {

    private final CarMapper carMapper;
    private final GarageMapper garageMapper;

    public MaintenanceResponse toMaintenanceResponse(MaintenanceEntity maintenanceEntity) {
        String carName = String.format("%s - %s", maintenanceEntity.getCar().getMake(), maintenanceEntity.getCar().getModel());
        return MaintenanceResponse.builder()
                .id(maintenanceEntity.getId())
                .carId(maintenanceEntity.getCar().getId())
                .carName(carName)
                .serviceType(maintenanceEntity.getServiceType())
                .scheduledDate(maintenanceEntity.getScheduledDate())
                .garageId(maintenanceEntity.getGarage().getId())
                .garageName(maintenanceEntity.getGarage().getName())
                .build();
    }

    public MaintenanceEntity toMaintenanceEntity(MaintenanceRequest maintenanceRequest, GarageEntity garage, CarEntity car) {
        return MaintenanceEntity.builder()
                .garage(garage)
                .car(car)
                .serviceType(maintenanceRequest.serviceType())
                .scheduledDate(maintenanceRequest.scheduledDate())
                .build();
    }

    public MaintenanceEntity toMaintenanceEntityUpdate(CarEntity car, MaintenanceEntity maintenance, GarageEntity garage, MaintenanceRequest maintenanceRequest) {
        maintenance.setGarage(garage);
        maintenance.setScheduledDate(maintenanceRequest.scheduledDate());
        maintenance.setCar(car);
        maintenance.setServiceType(maintenanceRequest.serviceType());
        return maintenance;
    }

}
