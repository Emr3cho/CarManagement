package fmi.wsp.carmanagement.garage.domain;

import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;

    @PostMapping()
    public ResponseEntity<GarageEntity> createGarage(@RequestBody GarageRequest garageRequest) {
        GarageEntity GarageEntity = garageService.saveGarage(garageRequest);
        return ResponseEntity.status(200).body(GarageEntity);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GarageEntity> fetchGarage(@PathVariable long id) {
        GarageEntity GarageEntity = garageService.fetchGarage(id);
        return ResponseEntity.status(200).body(GarageEntity);
    }
    @GetMapping()
    public ResponseEntity<List<GarageEntity>> fetchAllGarage() {
        List<GarageEntity> GarageEntity = garageService.fetchAllGarages();
        return ResponseEntity.status(200).body(GarageEntity);
    }
}
