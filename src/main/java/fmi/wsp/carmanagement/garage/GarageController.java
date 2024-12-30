package fmi.wsp.carmanagement.garage;

import fmi.wsp.carmanagement.garage.DTO.GarageReportResponse;
import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.DTO.GarageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;

    @PostMapping()
    public ResponseEntity<GarageResponse> createGarage(@RequestBody GarageRequest garageRequest) {
        GarageResponse garageResponse = garageService.saveGarage(garageRequest);
        return ResponseEntity.status(200).body(garageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageResponse> fetchGarage(@PathVariable long id) {
        GarageResponse garage = garageService.fetchGarage(id);
        return ResponseEntity.status(200).body(garage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarageResponse> updateGarage(@PathVariable long id, @RequestBody GarageRequest garageRequest) {
        GarageResponse updatedGarage = garageService.updateGarage(garageRequest, id);
        return ResponseEntity.status(200).body(updatedGarage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGarage(@PathVariable long id) {
        this.garageService.deleteGarage(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping()
    //TODO:
    public ResponseEntity<List<GarageResponse>> fetchAllGarage(@RequestParam(required = false) String city) {
        List<GarageResponse> garages = garageService.fetchAllGarages(city);
        return ResponseEntity.status(200).body(garages);
    }

    @GetMapping("/dailyAvailabilityReport")
    public List<GarageReportResponse> getCapacityReport(
            @RequestParam Long garageId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return garageService.getCapacityReport(garageId, startDate, endDate);
    }
}
