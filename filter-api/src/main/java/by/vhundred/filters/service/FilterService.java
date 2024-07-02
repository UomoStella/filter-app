package by.vhundred.filters.service;

import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.entity.Filter;

import java.util.List;

public interface FilterService {
    List<Filter> getAllFilters();
    FilterDto getFilterById(Long id);
    void save(FilterDto filterDto);
    List<FilterPageDto> getAllFilterPageInfo();
}
