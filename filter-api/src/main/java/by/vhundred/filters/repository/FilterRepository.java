package by.vhundred.filters.repository;

import by.vhundred.filters.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilterRepository extends JpaRepository<Filter, UUID> {
}
