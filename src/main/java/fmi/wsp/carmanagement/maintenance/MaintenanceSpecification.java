package fmi.wsp.carmanagement.maintenance;

import fmi.wsp.carmanagement.car.CarEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceSpecification {
    public static Specification<MaintenanceEntity> filterMaintenance(Long carId, Long garageId, LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (carId != null && carId > 0) {
                predicates.add(criteriaBuilder.equal(root.join("car").get("id"), carId));
            }
            if (garageId != null && garageId > 0) {
                predicates.add(criteriaBuilder.equal(root.join("garage").get("id"), garageId));
            }
            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("scheduledDate"), fromDate));
            }
            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("scheduledDate"), toDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
