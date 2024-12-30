package fmi.wsp.carmanagement.maintenance;

import fmi.wsp.carmanagement.car.CarEntity;
import fmi.wsp.carmanagement.car.CarRepository;
import fmi.wsp.carmanagement.common.MaintenanceMapper;
import fmi.wsp.carmanagement.common.NoCapacityException;
import fmi.wsp.carmanagement.common.ResourceNotFoundException;
import fmi.wsp.carmanagement.garage.GarageEntity;
import fmi.wsp.carmanagement.garage.GarageRepository;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceReportResponse;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceRequest;
import fmi.wsp.carmanagement.maintenance.DTO.MaintenanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final GarageRepository garageRepository;
    private final CarRepository carRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceResponse fetchMaintenance(long id) {
        return maintenanceMapper.toMaintenanceResponse(maintenanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Maintenance with id %s is not available!", id))));
    }

    @Override
    public List<MaintenanceResponse> fetchMaintenancesByCriteria(long carId, long garageId, LocalDate startDate, LocalDate toDate) {
        Specification<MaintenanceEntity> spec = MaintenanceSpecification.filterMaintenance(carId, garageId, startDate, toDate);
        List<MaintenanceEntity> maintenances = maintenanceRepository.findAll(spec);
        return maintenances.stream().map(maintenanceMapper::toMaintenanceResponse).toList();
    }

    @Override
    public MaintenanceResponse createMaintenance(MaintenanceRequest maintenanceRequest) {
        CarEntity car = carRepository.findById(maintenanceRequest.carId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Car with id %s is not available!", maintenanceRequest.carId())));
        GarageEntity garage = garageRepository.findById(maintenanceRequest.garageId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Garage with id %s is not available!", maintenanceRequest.garageId())));
        int garageBusyness = maintenanceRepository.countMaintenanceEntitiesByGarageAndScheduledDate(garage, maintenanceRequest.scheduledDate());
        if (garageBusyness >= garage.getCapacity())
            throw new NoCapacityException("Garage capacity exceeded maximum capacity!");
        MaintenanceEntity maintenance = maintenanceMapper.toMaintenanceEntity(maintenanceRequest, garage, car);
        return maintenanceMapper.toMaintenanceResponse(maintenanceRepository.save(maintenance));
    }

    @Override
    public MaintenanceResponse updateMaintenance(long id, MaintenanceRequest maintenanceRequest) {
        CarEntity car = carRepository.findById(maintenanceRequest.carId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Car with id %s is not available!", maintenanceRequest.carId())));
        GarageEntity garage = garageRepository.findById(maintenanceRequest.garageId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Garage with id %s is not available!", maintenanceRequest.garageId())));
        MaintenanceEntity maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Maintenance with id %s is not available!", id)));
        maintenance = maintenanceMapper.toMaintenanceEntityUpdate(car, maintenance, garage, maintenanceRequest);
        return maintenanceMapper.toMaintenanceResponse(maintenanceRepository.save(maintenance));
    }

    @Override
    public void deleteMaintenance(long id) {
        maintenanceRepository.deleteById(id);
    }

    @Override
    public List<MaintenanceReportResponse> fetchMaintenanceDetails(Long garageId, String startMonth, String endMonth) {
        LocalDate startDate = LocalDate.parse(startMonth + "-01");
        LocalDate endDate = LocalDate.parse(endMonth + "-01");

        List<Object[]> rawData = maintenanceRepository.getMonthlyRequestsReport(garageId, startDate, endDate);

        Map<String, Long> dataMap = rawData.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> ((Number) row[1]).longValue()));

        List<MaintenanceReportResponse> report = new ArrayList<>();
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            String month = current.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            report.add(MaintenanceReportResponse.builder().yearMonth(month).requests(dataMap.getOrDefault(month, 0L)).build());
            current = current.plusMonths(1);
        }

        return report;
    }
}
