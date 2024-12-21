package fmi.wsp.carmanagement.garage.DTO;

import lombok.Builder;

@Builder
public record GarageRequest(String name, String location, String city, Integer capacity){ }
