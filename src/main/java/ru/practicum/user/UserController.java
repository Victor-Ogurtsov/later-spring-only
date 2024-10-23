package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.dto.UserDto;
import ru.practicum.user.dto.UserMapper;
import ru.practicum.user.dto.UserRequestDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userMapper.toListUserDto(userService.getAllUsers());
    }

    @PostMapping
    public UserDto saveNewUser(@RequestBody UserRequestDto userRequestDto) {
        return userMapper.toUserDto(userService.saveUser(userRequestDto));
    }
}
