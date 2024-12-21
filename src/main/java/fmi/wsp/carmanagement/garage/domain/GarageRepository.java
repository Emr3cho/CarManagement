package fmi.wsp.carmanagement.garage.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<GarageEntity, Long> {
}
