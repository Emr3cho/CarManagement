package fmi.wsp.carmanagement.garage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface GarageRepository extends JpaRepository<GarageEntity, Long> {
    List<GarageEntity> findAllByIdIn(Collection<Long> id);
}
