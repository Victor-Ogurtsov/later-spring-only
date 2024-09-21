package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
class UserServiceImpl implements UserService {
    private final UserRepository repository;
    //@Value("${settings.num}")
    private final int num;
    private final int q;

    @Autowired
    public UserServiceImpl(UserRepository repository,@Value("${settings.num}") int num,
                           @Value("${settings.q}") int q) {
        this.repository = repository;
        this.num = num;
        this.q = q;
    }

    @Override
    public List<User> getAllUsers() {
        System.out.println("Внедрены настройки num и q из файла application.properties с внешними настройками" + num + " + " + q);
        log.info("Сервис - Возвращаем список пользователей");
        return repository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }
}
