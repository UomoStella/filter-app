package by.vhundred.filters.controller;

import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.entity.Filter;
import by.vhundred.filters.service.FilterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filters")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;


    @GetMapping
    public List<Filter> getAllFilters() {
        return filterService.getAllFilters();
    }

    @GetMapping("/{id}")
    public FilterDto getFilterById(@PathVariable Long id) {
        return filterService.getFilterById(id);
    }

    @PostMapping
    public void saveFilter(@Valid @RequestBody FilterDto filter) {
        filterService.save(filter);
    }

    @GetMapping("/page-info")
    public List<FilterPageDto> getAllFilterPageInfo() {
        return filterService.getAllFilterPageInfo();
    }
}