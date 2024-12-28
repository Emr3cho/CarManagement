package fmi.wsp.carmanagement.common;

import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.DTO.GarageResponse;
import fmi.wsp.carmanagement.garage.GarageEntity;
import org.springframework.stereotype.Component;

@Component
public class GarageMapper {
    public GarageEntity ToGarageEntity(GarageRequest garageRequest) {
        return GarageEntity.builder()
                .city(garageRequest.city())
                .name(garageRequest.name())
                .capacity(garageRequest.capacity())
                .location(garageRequest.location())
                .build();
    }

    public GarageResponse toGarageResponse(GarageEntity garageEntity) {
        return GarageResponse.builder()
                .id(garageEntity.getId())
                .location(garageEntity.getLocation())
                .capacity(garageEntity.getCapacity())
                .name(garageEntity.getName())
                .city(garageEntity.getCity())
                .build();
    }
}
