package fmi.wsp.carmanagement.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
}