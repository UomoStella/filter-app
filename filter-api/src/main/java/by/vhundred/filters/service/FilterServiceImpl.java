package by.vhundred.filters.service;

import by.vhundred.filters.dto.ConditionDto;
import by.vhundred.filters.dto.CriteriaDto;
import by.vhundred.filters.dto.FilterDto;
import by.vhundred.filters.dto.FilterPageDto;
import by.vhundred.filters.entity.Condition;
import by.vhundred.filters.entity.Criteria;
import by.vhundred.filters.entity.CriteriaType;
import by.vhundred.filters.entity.Filter;
import by.vhundred.filters.mapper.FilterMapper;
import by.vhundred.filters.repository.ConditionRepository;
import by.vhundred.filters.repository.CriteriaRepository;
import by.vhundred.filters.repository.CriteriaTypeRepository;
import by.vhundred.filters.repository.FilterRepository;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FilterServiceImpl implements FilterService {

    private FilterRepository filterRepository;
    private CriteriaRepository criteriaRepository;
    private ConditionRepository conditionRepository;
    private CriteriaTypeRepository criteriaTypeRepository;
    private FilterMapper filterMapper;

    @Override
    public List<Filter> getAllFilters() {
        return filterRepository.findAll();
    }

    @Override
    public FilterDto getFilterById(Long id) {
        Filter filter = filterRepository.findById(id).orElseThrow(() -> new RuntimeException("Filter not found"));
        List<CriteriaDto> criteriaList = criteriaRepository.findAllByFilterId(id).stream().map(filterMapper::criteriaToCriteriaDto).toList();

        return filterMapper.filterToFilterDto(filter, criteriaList);
    }

    @Override
    @Transactional
    public void save(FilterDto filterDto) {
        Filter filter = filterMapper.filterDtoToFilter(filterDto);
        filterRepository.save(filter);

        List<Long> conditionIds = filterDto.getCriteria().stream().map(CriteriaDto::getConditionId).toList();
        List<Condition> conditions = conditionRepository.findAllById(conditionIds);
        List<Criteria> criteriaToSave = filterDto.getCriteria().stream().map(criteria -> filterMapper.criteriaDtoToCriteria(criteria, conditions, filter.getId())).toList();

        if(filterDto.getId() != null) {
            List<Criteria> criteriaToDelete = criteriaRepository.findAllByFilterId(filterDto.getId()).stream()
                    .filter(toDelete ->
                            criteriaToSave.stream().noneMatch(toSave -> toDelete.getId().equals(toSave.getId()))
                    ).toList();

            criteriaRepository.deleteAll(criteriaToDelete);
        }

        criteriaRepository.saveAll(criteriaToSave);
    }

    @Override
    @Transactional
    public List<FilterPageDto> getAllFilterPageInfo() {
        final List<CriteriaType> criteriaTypes = criteriaTypeRepository.findAll();

        return criteriaTypes.stream().map(type -> {
            List<ConditionDto> conditions = conditionRepository.findAllByCriteriaType(type.getId()).stream()
                    .map(filterMapper::conditionToConditionDto)
                    .toList();
            return filterMapper.mapCriteriaTypeByCriteriaTypeAndCriteria(type, conditions);
        }).toList();
    }
}
