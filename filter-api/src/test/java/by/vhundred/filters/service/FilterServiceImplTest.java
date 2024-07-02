package by.vhundred.filters.service;
import by.vhundred.filters.dto.ConditionDto;
import by.vhundred.filters.dto.CriteriaDto;
import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.dto.enums.Type;
import by.vhundred.filters.entity.Condition;
import by.vhundred.filters.entity.Criteria;
import by.vhundred.filters.entity.CriteriaType;
import by.vhundred.filters.entity.Filter;
import by.vhundred.filters.helper.TestHelper;
import by.vhundred.filters.mapper.FilterMapper;
import by.vhundred.filters.repository.ConditionRepository;
import by.vhundred.filters.repository.CriteriaRepository;
import by.vhundred.filters.repository.CriteriaTypeRepository;
import by.vhundred.filters.repository.FilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilterServiceImplTest {
    @Mock
    private FilterRepository filterRepository;

    @Mock
    private CriteriaRepository criteriaRepository;

    @Mock
    private ConditionRepository conditionRepository;

    @Mock
    private CriteriaTypeRepository criteriaTypeRepository;

    @Mock
    private FilterMapper filterMapper;

    @InjectMocks
    private FilterServiceImpl filterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFilters() {
        List<Filter> filters = List.of(new Filter());
        when(filterRepository.findAll()).thenReturn(filters);

        List<Filter> result = filterService.getAllFilters();

        assertEquals(filters, result);
        verify(filterRepository, times(1)).findAll();
    }

    @Test
    void testGetFilterById() {
        UUID filterId = new UUID(1,2);
        Filter filter = TestHelper.createFilter();
        List<Criteria> criteriaList = List.of(TestHelper.createCriteria());
        List<CriteriaDto> criteriaDtos = criteriaList.stream()
                .map(filterMapper::criteriaToCriteriaDto)
                .collect(Collectors.toList());
        FilterDto filterDto = new FilterDto();

        when(filterRepository.findById(filterId)).thenReturn(Optional.of(filter));
        when(criteriaRepository.findAllByFilterId(filterId)).thenReturn(criteriaList);
        when(filterMapper.filterToFilterDto(filter, criteriaDtos)).thenReturn(filterDto);

        FilterDto result = filterService.getFilterById(filterId);

        assertEquals(filterDto, result);
        verify(filterRepository, times(1)).findById(filterId);
        verify(criteriaRepository, times(1)).findAllByFilterId(filterId);
    }

    @Test
    void testGetAllFilterPageInfo() {
        List<CriteriaType> criteriaTypes = List.of(TestHelper.createCriteriaType());
        List<ConditionDto> conditionDtos = List.of(new ConditionDto(UUID.randomUUID(), "Name"));
        FilterPageDto filterPageDto = new FilterPageDto(UUID.randomUUID(), "TypeName", Type.NUMBER, conditionDtos);

        when(criteriaTypeRepository.findAll()).thenReturn(criteriaTypes);
        when(conditionRepository.findAllByCriteriaType(any())).thenReturn(List.of(new Condition()));
        when(filterMapper.conditionToConditionDto(any(Condition.class))).thenReturn(new ConditionDto(UUID.randomUUID(), "Name"));
        when(filterMapper.mapCriteriaTypeByCriteriaTypeAndCriteria(any(), anyList())).thenReturn(filterPageDto);

        List<FilterPageDto> result = filterService.getAllFilterPageInfo();

        assertEquals(1, result.size());
        assertEquals(filterPageDto, result.get(0));
        verify(criteriaTypeRepository, times(1)).findAll();
    }
}
