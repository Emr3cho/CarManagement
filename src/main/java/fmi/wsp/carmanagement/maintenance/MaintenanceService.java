package fmi.wsp.carmanagement.maintenance;

import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceReportRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceReportResponse;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceResponse;

public interface MaintenanceService {
    MaintenanceResponse fetchMaintenance(long id);
    MaintenanceResponse createMaintenance(MaintenanceRequest maintenanceRequest);
    MaintenanceResponse updateMaintenance(long id, MaintenanceRequest maintenanceRequest);
    void deleteMaintenance(long id);
    MaintenanceReportResponse fetchMaintenanceDetails(MaintenanceReportRequest maintenanceReportRequest);
}
