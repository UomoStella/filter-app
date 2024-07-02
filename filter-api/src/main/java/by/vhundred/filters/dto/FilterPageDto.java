package by.vhundred.filters.dto;

import by.vhundred.filters.dto.enums.Type;
import java.util.List;

public record FilterPageDto (Long typeId, String typeName, Type type, List<ConditionDto> conditions) {
}
