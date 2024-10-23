package ru.practicum.user.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.practicum.user.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRequestDtoMapper {

    User fromUserRequestDto(UserRequestDto userRequestDto);
}
