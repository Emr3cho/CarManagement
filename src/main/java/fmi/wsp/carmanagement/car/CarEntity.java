package fmi.wsp.carmanagement.car;

import fmi.wsp.carmanagement.garage.GarageEntity;
import fmi.wsp.carmanagement.maintenance.MaintenanceEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "car")
@AllArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;

    @ManyToMany(mappedBy = "cars")
    private List<GarageEntity> garages;

    @OneToMany(mappedBy = "car")
    private List<MaintenanceEntity> maintenances;
}
