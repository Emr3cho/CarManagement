package fmi.wsp.carmanagement.maintenance.DTO;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MaintenanceRequest(Long carId, String serviceType, LocalDate scheduledDate, Long garageId) {
}
