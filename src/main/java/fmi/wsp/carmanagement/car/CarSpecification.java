package fmi.wsp.carmanagement.car;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class CarSpecification {

    public static Specification<CarEntity> filterCars(String carMake, Long garageId, Integer fromYear, Integer toYear) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (carMake != null && !carMake.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("make"), carMake));
            }
            if (garageId != null && garageId > 0) {
                predicates.add(criteriaBuilder.equal(root.join("garages").get("id"), garageId));
            }
            if (fromYear != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productionYear"), fromYear));
            }
            if (toYear != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("productionYear"), toYear));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
