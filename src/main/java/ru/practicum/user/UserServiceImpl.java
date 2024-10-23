package ru.practicum.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.user.dto.UserRequestDto;
import ru.practicum.user.dto.UserRequestDtoMapper;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final int num1;
    private final int num2;

    private final UserRequestDtoMapper userRequestDtoMapper;

    Pageable page = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));

    @Autowired
    public UserServiceImpl(UserRepository repository,@Value("${settings.num1}") int num1,
                           @Value("${settings.num2}") int num2, UserRequestDtoMapper userRequestDtoMapper) {
        this.repository = repository;
        this.num1 = num1;
        this.num2 = num2;
        this.userRequestDtoMapper = userRequestDtoMapper;
    }

    @Override
    public List<User> getAllUsers() {
        System.out.println("Внедрены настройки num и q из файла application.properties с внешними настройками " + num1 + " + " + num2);
        log.info("Сервис - Возвращаем список пользователей");
        Page<User> usersPage = repository.findAll(page);
        List<User> users = usersPage.getContent();
        nextPage(usersPage);
        int a = 2;
        return users;
    }

    @Override
    @Transactional
    public User saveUser(UserRequestDto userRequestDto) {
        User user = userRequestDtoMapper.fromUserRequestDto(userRequestDto);
        return repository.save(user);
    }

    private void nextPage(Page<User> usersPage) {
        if(usersPage.hasNext()){
            // если следующая страница существует, создаём её описание, чтобы запросить на следующей итерации цикла
            page = PageRequest.of(usersPage.getNumber() + 1, usersPage.getSize(), usersPage.getSort()); // или для простоты -- userPage.nextOrLastPageable()
        } else {
            page = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));
        }
    }
}
