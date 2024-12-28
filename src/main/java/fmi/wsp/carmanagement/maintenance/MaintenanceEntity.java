package fmi.wsp.carmanagement.maintenance;

import com.fasterxml.jackson.annotation.JsonFormat;
import fmi.wsp.carmanagement.car.CarEntity;
import fmi.wsp.carmanagement.garage.GarageEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maintenance")
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    private GarageEntity garage;

    @ManyToOne()
    private CarEntity car;

    private String serviceType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduledDate;
}
