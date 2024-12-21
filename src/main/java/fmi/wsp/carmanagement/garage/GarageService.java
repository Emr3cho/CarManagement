package fmi.wsp.carmanagement.garage;

import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.domain.GarageEntity;

import java.util.List;

public interface GarageService {
    GarageEntity fetchGarage(long garageId);
    List<GarageEntity> fetchAllGarages();
    GarageEntity saveGarage(GarageRequest garageRequest);
    GarageEntity updateGarage(GarageRequest garageRequest);
    void deleteGarage(long garageId);
}
