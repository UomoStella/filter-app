package by.vhundred.filters.service;

import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.entity.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<Filter> getAllFilters();
    FilterDto getFilterById(UUID id);
    void save(FilterDto filterDto);
    List<FilterPageDto> getAllFilterPageInfo();
}
