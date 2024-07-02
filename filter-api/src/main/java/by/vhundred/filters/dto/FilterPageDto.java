package by.vhundred.filters.dto;

import by.vhundred.filters.dto.enums.Type;
import java.util.List;
import java.util.UUID;

public record FilterPageDto (UUID typeId, String typeName, Type type, List<ConditionDto> conditions) {
}
