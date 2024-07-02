package by.vhundred.filters.repository;

import by.vhundred.filters.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, UUID> {

    @Query("""
            select c from Criteria c  
            left join c.condition co 
            left join co.criteriaType cT
            where c.filterId = ?1
            """)
    List<Criteria> findAllByFilterId(UUID filterId);
}
