package fmi.wsp.carmanagement.maintenance.domain;

import fmi.wsp.carmanagement.garage.domain.GarageEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Entity
@Builder
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maintenance")
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private GarageEntity garage;



}
