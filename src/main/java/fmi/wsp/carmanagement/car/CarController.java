package fmi.wsp.carmanagement.car;

import fmi.wsp.carmanagement.car.DTO.CarRequest;
import fmi.wsp.carmanagement.car.DTO.CarResponse;
import fmi.wsp.carmanagement.car.DTO.CarUpdateRequest;
import fmi.wsp.carmanagement.garage.DTO.GarageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> fetchCar(@PathVariable long id) {
        CarResponse car = carService.fetchCarById(id);
        return ResponseEntity.status(200).body(car);
    }

    @GetMapping()
    public ResponseEntity<List<CarResponse>> fetchAllCars(@RequestParam(required = false) String carMake,
                                                          @RequestParam(required = false) Long garageId,
                                                          @RequestParam(required = false) Integer fromYear,
                                                          @RequestParam(required = false) Integer toYear) {
        if(garageId == null) garageId = 0L;
        if(fromYear == null) fromYear = 0;
        if(toYear == null) toYear = 2100;

        List<CarResponse> carsByCriteria = carService.getAllCarsByCriteria(carMake, garageId, fromYear, toYear);
        return ResponseEntity.status(200).body(carsByCriteria);
    }

    @PostMapping()
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        CarResponse car = carService.createCar(carRequest);
        return ResponseEntity.status(200).body(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable long id, @RequestBody CarUpdateRequest carUpdateRequest) {
        CarResponse car = carService.updateCar(id, carUpdateRequest);
        return ResponseEntity.status(200).body(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
        return ResponseEntity.status(200).build();
    }

}
