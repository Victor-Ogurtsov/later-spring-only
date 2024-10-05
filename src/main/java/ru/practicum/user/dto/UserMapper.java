package ru.practicum.user.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.practicum.user.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


    @Named("instantToFormattedString")
    default String instantToFormattedString(Instant instant) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss").format(localDateTime);
    }

    User fromUserDto(UserDto userDto);

    @Mapping(target="registrDate", source = "registrationDate",
            qualifiedByName = "instantToFormattedString")
    UserDto toUserDto(User user);

    List<UserDto> toListUserDto(List<User> userList);
}
