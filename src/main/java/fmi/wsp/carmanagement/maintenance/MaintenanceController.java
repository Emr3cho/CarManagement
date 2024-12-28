package fmi.wsp.carmanagement.maintenance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    //TODO: Demo Request - http://localhost:8088/maintenance/monthlyRequestsReport?garageId=1&startMonth=2024-01&endMonth=2025-01
}
