package fmi.wsp.carmanagement.maintenance;

import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceReportRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceReportResponse;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {
    @Override
    public MaintenanceResponse fetchMaintenance(long id) {
        return null;
    }

    @Override
    public MaintenanceResponse createMaintenance(MaintenanceRequest maintenanceRequest) {
        return null;
    }

    @Override
    public MaintenanceResponse updateMaintenance(long id, MaintenanceRequest maintenanceRequest) {
        return null;
    }

    @Override
    public void deleteMaintenance(long id) {

    }

    @Override
    public MaintenanceReportResponse fetchMaintenanceDetails(MaintenanceReportRequest maintenanceReportRequest) {
        return null;
    }
}
