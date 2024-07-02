package by.vhundred.filters.mapper;
import by.vhundred.filters.dto.ConditionDto;
import by.vhundred.filters.dto.CriteriaDto;
import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.entity.Condition;
import by.vhundred.filters.entity.Criteria;
import by.vhundred.filters.entity.CriteriaType;
import by.vhundred.filters.entity.Filter;
import by.vhundred.filters.helper.TestHelper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FilterMapperTest {
    private final FilterMapper mapper = Mappers.getMapper(FilterMapper.class);

    @Test
    void testFilterToFilterDto() {
        Filter filter = TestHelper.createFilter();
        List<CriteriaDto> criteriaDtos = TestHelper.createCriteriaDtos();

        FilterDto filterDto = mapper.filterToFilterDto(filter, criteriaDtos);

        assertEquals(filter.getId(), filterDto.getId());
        assertEquals(filter.getName(), filterDto.getName());
        assertEquals(filter.getSelection(), filterDto.getSelection());
        assertEquals(criteriaDtos, filterDto.getCriteria());
    }

    @Test
    void testFilterDtoToFilter() {
        FilterDto filterDto = new FilterDto();
        filterDto.setId(UUID.randomUUID());
        filterDto.setName("Test Filter");
        filterDto.setSelection("SELECTION_1");

        Filter filter = mapper.filterDtoToFilter(filterDto);

        assertEquals(filterDto.getId(), filter.getId());
        assertEquals(filterDto.getName(), filter.getName());
        assertEquals(filterDto.getSelection(), filter.getSelection());
    }

    @Test
    void testCriteriaToCriteriaDto() {
        Criteria criteria = TestHelper.createCriteria();

        CriteriaDto criteriaDto = mapper.criteriaToCriteriaDto(criteria);

        assertEquals(criteria.getId(), criteriaDto.getId());
        assertEquals(criteria.getCondition().getId(), criteriaDto.getConditionId());
        assertEquals(criteria.getCondition().getCriteriaType().getId(), criteriaDto.getTypeId());
        assertEquals(criteria.getValue(), criteriaDto.getValue());
    }

    @Test
    void testConditionToConditionDto() {
        Condition condition = TestHelper.createCondition();

        ConditionDto conditionDto = mapper.conditionToConditionDto(condition);

        assertEquals(condition.getId(), conditionDto.id());
    }

    @Test
    void testMapCriteriaTypeByCriteriaTypeAndCriteria() {
        CriteriaType type = TestHelper.createCriteriaType();
        List<ConditionDto> conditionDtos = List.of(new ConditionDto(UUID.randomUUID(), ""));

        FilterPageDto filterPageDto = mapper.mapCriteriaTypeByCriteriaTypeAndCriteria(type, conditionDtos);

        assertEquals(type.getId(), filterPageDto.typeId());
        assertEquals(type.getName(), filterPageDto.typeName());
        assertEquals(conditionDtos, filterPageDto.conditions());
    }
}
