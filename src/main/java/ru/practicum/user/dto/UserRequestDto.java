package ru.practicum.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequestDto {
    private String firstName;

    private String lastName;

    private String email;
}