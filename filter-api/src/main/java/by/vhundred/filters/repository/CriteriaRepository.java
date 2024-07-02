package by.vhundred.filters.repository;

import by.vhundred.filters.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {

    @Query("""
            select c from Criteria c  
            left join c.condition co 
            left join co.criteriaType cT
            where c.filterId = ?1
            """)
    List<Criteria> findAllByFilterId(Long filterId);
}
