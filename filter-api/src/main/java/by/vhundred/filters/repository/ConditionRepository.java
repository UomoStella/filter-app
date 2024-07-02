package by.vhundred.filters.repository;

import by.vhundred.filters.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, UUID> {

    @Query("Select c From Condition c left join c.criteriaType ct where ct.id = ?1")
    List<Condition> findAllByCriteriaType(UUID criteriaTypeId);
}
