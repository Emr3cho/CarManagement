package fmi.wsp.carmanagement.garage;

import fmi.wsp.carmanagement.car.CarEntity;
import fmi.wsp.carmanagement.maintenance.MaintenanceEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "garage")
public class GarageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToMany()
    @JoinTable(
            name = "cars_garages",
            joinColumns = @JoinColumn(name = "garage_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<CarEntity> cars;

    @OneToMany(mappedBy = "garage")
    private List<MaintenanceEntity> maintenances;
}
