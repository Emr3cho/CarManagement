package fmi.wsp.carmanagement.garage.DTO;

import lombok.Builder;

@Builder
public record GarageResponse(long id, String name, String location, String city, int capacity) {
}
