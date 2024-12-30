package fmi.wsp.carmanagement.garage;

import fmi.wsp.carmanagement.garage.DTO.GarageReportResponse;
import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.DTO.GarageResponse;

import java.time.LocalDate;
import java.util.List;

public interface GarageService {
    GarageResponse fetchGarage(long garageId);
    List<GarageEntity> findSpecificGarages(List<Long> garageIds);
    List<GarageResponse> fetchAllGarages(String city);
    GarageResponse saveGarage(GarageRequest garageRequest);
    GarageResponse updateGarage(GarageRequest garageRequest, long garageId);
    void deleteGarage(long garageId);
    List<GarageReportResponse> getCapacityReport(Long garageId, LocalDate startDate, LocalDate endDate);
}
