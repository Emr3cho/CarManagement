package fmi.wsp.carmanagement.garage.DTO;

import java.time.LocalDate;

public record GarageReportResponse (LocalDate date, long requests, long availableCapacity) {
}
