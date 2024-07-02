package by.vhundred.filters.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CriteriaDto {
    private UUID id;

    @NotEmpty(message = "Value must be filled in.")
    private String value;

    @NotNull(message = "TypeId must be filled in.")
    private UUID typeId;

    @NotNull(message = "СonditionId must be filled in.")
    private UUID conditionId;
}
