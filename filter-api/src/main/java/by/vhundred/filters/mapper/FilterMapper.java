package by.vhundred.filters.mapper;

import by.vhundred.filters.dto.ConditionDto;
import by.vhundred.filters.dto.CriteriaDto;
import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.entity.Condition;
import by.vhundred.filters.entity.Criteria;
import by.vhundred.filters.entity.CriteriaType;
import by.vhundred.filters.entity.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FilterMapper {

    FilterDto filterToFilterDto(Filter filter, List<CriteriaDto> criteria);

    Filter filterDtoToFilter(FilterDto filter);

    @Mapping(source = "condition.id", target = "conditionId")
    @Mapping(source = "condition.criteriaType.id", target = "typeId")
    CriteriaDto criteriaToCriteriaDto(Criteria criteria);

    @Mapping(expression = "java(findCondition(conditions, criteria.getConditionId()))", target = "condition")
    Criteria criteriaDtoToCriteria(CriteriaDto criteria, List<Condition> conditions, UUID filterId);

    ConditionDto conditionToConditionDto(Condition condition);

    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "type.name", target = "typeName")
    FilterPageDto mapCriteriaTypeByCriteriaTypeAndCriteria(CriteriaType type, List<ConditionDto> conditions);


    default Condition findCondition(List<Condition> conditions, UUID conditionId) {
        return conditions.stream()
                .filter( condition -> condition.getId().equals(conditionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Condition not found!"));
    }
}
