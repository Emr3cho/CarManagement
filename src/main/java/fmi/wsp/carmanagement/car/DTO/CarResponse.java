package fmi.wsp.carmanagement.car.DTO;

import fmi.wsp.carmanagement.garage.DTO.GarageResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record CarResponse(long id, String make, String model, int productionYear, String licensePlate, List<GarageResponse> garages) {

}
