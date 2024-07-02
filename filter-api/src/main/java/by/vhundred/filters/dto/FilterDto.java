package by.vhundred.filters.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FilterDto {

    private Long id;

    private String name;

    private String selection;

    private List<CriteriaDto> criteria;
}
