package ru.practicum.user;

import ru.practicum.user.dto.UserRequestDto;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(UserRequestDto userRequestDto);
}
