package by.vhundred.filters.controller;

import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.entity.Filter;
import by.vhundred.filters.helper.TestHelper;
import by.vhundred.filters.service.FilterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilterController.class)
class FilterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilterService filterService;

    @Autowired
    private ObjectMapper objectMapper;

    private FilterDto filterDto;
    private List<Filter> filterList;
    private List<FilterPageDto> filterPageDtoList;

    @BeforeEach
    void setUp() {
        Filter filter = TestHelper.createFilter();
        filterDto = TestHelper.createFilterDto();
        filterList = List.of(filter);
        filterPageDtoList = List.of(TestHelper.createFilterPageDto());
    }

    @Test
    void testGetAllFilters() throws Exception {
        when(filterService.getAllFilters()).thenReturn(filterList);

        mockMvc.perform(get("/api/filters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(filterList.size()));

        verify(filterService, times(1)).getAllFilters();
    }

    @Test
    void testGetFilterById() throws Exception {
        UUID filterId = filterDto.getId();
        when(filterService.getFilterById(filterId)).thenReturn(filterDto);

        mockMvc.perform(get("/api/filters/{id}", filterId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());

        verify(filterService, times(1)).getFilterById(filterId);
    }

    @Test
    void testGetAllFilterPageInfo() throws Exception {
        when(filterService.getAllFilterPageInfo()).thenReturn(filterPageDtoList);

        mockMvc.perform(get("/api/filters/page-info")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(filterPageDtoList.size()));

        verify(filterService, times(1)).getAllFilterPageInfo();
    }
}
