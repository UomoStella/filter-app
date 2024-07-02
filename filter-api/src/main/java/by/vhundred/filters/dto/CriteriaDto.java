package by.vhundred.filters.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CriteriaDto {
    private Long id;
    @NotEmpty
    private String value;
    private Long typeId;
    private Long conditionId;
}
