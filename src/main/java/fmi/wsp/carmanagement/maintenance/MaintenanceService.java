package fmi.wsp.carmanagement.maintenance;

import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceReportResponse;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceService {
    MaintenanceResponse fetchMaintenance(long id);
    List<MaintenanceResponse> fetchMaintenancesByCriteria(long carId, long garageId, LocalDate startDate, LocalDate toDate);
    MaintenanceResponse createMaintenance(MaintenanceRequest maintenanceRequest);
    MaintenanceResponse updateMaintenance(long id, MaintenanceRequest maintenanceRequest);
    void deleteMaintenance(long id);
    List<MaintenanceReportResponse> fetchMaintenanceDetails(Long garageId, String startMonth, String endMonth);
}
