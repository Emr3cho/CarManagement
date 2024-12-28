package fmi.wsp.carmanagement.car;

import fmi.wsp.carmanagement.car.DTO.CarRequest;
import fmi.wsp.carmanagement.car.DTO.CarResponse;
import fmi.wsp.carmanagement.car.DTO.CarUpdateRequest;

import java.util.List;

public interface CarService {
    CarResponse fetchCarById(long carId);
    CarResponse updateCar(long carId, CarUpdateRequest request);
    CarResponse createCar(CarRequest carRequest);
    void deleteCar(long carId);
    List<CarResponse> getAllCarsByCriteria(String carMake, long garageId, int fromYear, int toYear);
}
