package fmi.wsp.carmanagement.maintenance.DTO;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MaintenanceResponse(Long id, Long carId, String carName, String serviceType, LocalDate scheduledDate, Long garageId, String garageName) {
}
