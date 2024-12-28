package fmi.wsp.carmanagement.garage;

import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.DTO.GarageResponse;

import java.util.List;

public interface GarageService {
    GarageResponse fetchGarage(long garageId);
    List<GarageEntity> findSpecificGarages(List<Long> garageIds);
    List<GarageResponse> fetchAllGarages();
    GarageResponse saveGarage(GarageRequest garageRequest);
    GarageResponse updateGarage(GarageRequest garageRequest, long garageId);
    void deleteGarage(long garageId);
}
