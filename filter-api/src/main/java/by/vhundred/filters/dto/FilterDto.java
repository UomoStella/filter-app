package by.vhundred.filters.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FilterDto {

    private UUID id;

    @NotEmpty(message = "Filter name must be filled in.")
    private String name;

    private String selection;

    @Valid
    private List<CriteriaDto> criteria;
}
