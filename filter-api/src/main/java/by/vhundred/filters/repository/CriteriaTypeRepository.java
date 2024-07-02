package by.vhundred.filters.repository;

import by.vhundred.filters.entity.CriteriaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CriteriaTypeRepository extends JpaRepository<CriteriaType, UUID> {
}
