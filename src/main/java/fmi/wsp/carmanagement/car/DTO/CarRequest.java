package fmi.wsp.carmanagement.car.DTO;

import lombok.Builder;

import java.util.List;

@Builder
public record CarRequest(String make, String model, int productionYear, String licensePlate, List<Long> garageIds) {
}
