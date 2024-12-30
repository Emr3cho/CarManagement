package fmi.wsp.carmanagement.maintenance;

import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceReportResponse;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.tomcat.util.http.FastHttpDateFormat.parseDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> fetchMaintenanceById(@PathVariable Long id) {
        MaintenanceResponse maintenance = maintenanceService.fetchMaintenance(id);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceResponse>> fetchAllMaintenancesByCriteria(@RequestParam(required = false) Long carId,
                                                                                    @RequestParam(required = false) Long garageId,
                                                                                    @RequestParam(required = false) String startDate,
                                                                                    @RequestParam(required = false) String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        if (carId == null) carId = 0L;
        if (garageId == null) garageId = 0L;
        List<MaintenanceResponse> maintenances = maintenanceService.fetchMaintenancesByCriteria(carId, garageId, start, end);
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/monthlyRequestsReport")
    public ResponseEntity<List<MaintenanceReportResponse>> monthlyRequestsReport(@RequestParam Long garageId,
                                                                           @RequestParam String startMonth,
                                                                           @RequestParam String endMonth){
        var response = maintenanceService.fetchMaintenanceDetails(garageId, startMonth, endMonth);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MaintenanceResponse> createMaintenance(@RequestBody MaintenanceRequest maintenanceRequest) {
        MaintenanceResponse maintenance = maintenanceService.createMaintenance(maintenanceRequest);
        return ResponseEntity.ok(maintenance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(@PathVariable long id, @RequestBody MaintenanceRequest maintenanceRequest) {
        MaintenanceResponse maintenanceResponse = maintenanceService.updateMaintenance(id, maintenanceRequest);
        return ResponseEntity.ok(maintenanceResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaintenance(@PathVariable long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.status(200).build();
    }
}
