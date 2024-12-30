package fmi.wsp.carmanagement.maintenance.DTO;

import lombok.Builder;

@Builder
public record MaintenanceReportResponse(String yearMonth, long requests) {

}
