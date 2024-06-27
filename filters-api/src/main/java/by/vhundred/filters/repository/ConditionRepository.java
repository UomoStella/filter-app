package by.vhundred.filters.repository;

import by.vhundred.filters.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    @Query("Select c From Condition left join c.criteriaType ct where ct.id = ?1")
    List<Condition> findAllByCriteriaType(Long criteriaTypeId);
}
