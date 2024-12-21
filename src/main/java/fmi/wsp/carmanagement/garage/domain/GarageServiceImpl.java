package fmi.wsp.carmanagement.garage.domain;

import fmi.wsp.carmanagement.common.ResourceNotFoundException;
import fmi.wsp.carmanagement.garage.DTO.GarageRequest;
import fmi.wsp.carmanagement.garage.GarageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;
    private final ModelMapper modelMapper;

    @Override
    public GarageEntity fetchGarage(long garageId) {
        return garageRepository.findById(garageId).orElseThrow(() -> new ResourceNotFoundException(String.format("Garage with id %s is not available!", garageId)));
    }

    @Override
    public List<GarageEntity> fetchAllGarages() {
        return garageRepository.findAll();
    }

    @Override
    public GarageEntity saveGarage(GarageRequest garageRequest) {
        GarageEntity garageEntity = GarageEntity.builder()
                .city(garageRequest.city())
                .name(garageRequest.name())
                .capacity(garageRequest.capacity())
                .location(garageRequest.location()).build();
        return garageRepository.save(garageEntity);
    }

    @Override
    public GarageEntity updateGarage(GarageRequest garageRequest) {
        return null;
    }

    @Override
    public void deleteGarage(long garageId) {

    }
}
