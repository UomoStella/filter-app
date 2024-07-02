package by.vhundred.filters.helper;

import by.vhundred.filters.dto.ConditionDto;
import by.vhundred.filters.dto.CriteriaDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.enums.Type;
import by.vhundred.filters.entity.Condition;
import by.vhundred.filters.entity.Criteria;
import by.vhundred.filters.entity.CriteriaType;
import by.vhundred.filters.entity.Filter;

import java.util.List;
import java.util.UUID;

public class TestHelper {
    public static Filter createFilter() {
        Filter filter = new Filter();
        filter.setId(UUID.randomUUID());
        filter.setName("Test Filter");
        filter.setSelection("SELECTION_1");
        return filter;
    }

    public static Criteria createCriteria() {
        Criteria criteria = new Criteria();
        criteria.setId(UUID.randomUUID());
        criteria.setCondition(createCondition());
        criteria.setValue("Test Value");
        criteria.setFilterId(UUID.randomUUID());
        return criteria;
    }

    public static Condition createCondition() {
        Condition condition = new Condition();
        condition.setId(UUID.randomUUID());
        condition.setCriteriaType(createCriteriaType());
        return condition;
    }

    public static CriteriaType createCriteriaType() {
        CriteriaType type = new CriteriaType();
        type.setId(UUID.randomUUID());
        type.setName("Test Type");
        return type;
    }

    public static List<Condition> createConditions() {
        return List.of(createCondition());
    }

    public static List<CriteriaDto> createCriteriaDtos() {
        CriteriaDto criteriaDto = new CriteriaDto();
        criteriaDto.setConditionId(UUID.randomUUID());
        criteriaDto.setTypeId(UUID.randomUUID());
        criteriaDto.setValue("Test Value");
        return List.of(criteriaDto);
    }

    public static FilterDto createFilterDto() {
        FilterDto filterDto = new FilterDto();
        filterDto.setId(UUID.randomUUID());
        filterDto.setName("Test Filter");
        filterDto.setSelection("SELECTION_1");

        CriteriaDto criteriaDto = new CriteriaDto();
        criteriaDto.setId(UUID.randomUUID());
        criteriaDto.setConditionId(UUID.randomUUID());
        criteriaDto.setTypeId(UUID.randomUUID());
        criteriaDto.setValue("Test Value");

        filterDto.setCriteria(List.of(criteriaDto));
        return filterDto;
    }

    public static FilterPageDto createFilterPageDto() {
        return new FilterPageDto(UUID.randomUUID(), "Test Type", Type.NUMBER,
                List.of(new ConditionDto(UUID.randomUUID(), "Test Condition")));

    }
}
