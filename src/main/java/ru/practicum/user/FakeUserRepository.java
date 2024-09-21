package ru.practicum.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FakeUserRepository implements UserRepository {

    private final List<User> FAKE_USERS = createManyFakeUsers(2);
    private final int q;

    @Autowired
    public FakeUserRepository(@Value("${settings.q}") int q) {
        this.q = q;
    }

    @Override
    public List<User> findAll() {
        return FAKE_USERS;
    }

    @Override
    public User save(User user) {
        throw new UnsupportedOperationException("Метод save() ещё не готов");
    }

    private List<User> createManyFakeUsers(int count) {
        List<User> fakeUsers = new ArrayList<>();
        for (long id = 1; id <= count; id++) {
            fakeUsers.add(createFakeUser(id));
        }
        return Collections.unmodifiableList(fakeUsers);
    }

    private User createFakeUser(long id) {
        User fakeUser = new User();
        fakeUser.setId(id);
        fakeUser.setEmail("mail" + id + "@example.com" + " " + q);
        fakeUser.setName("Akakiy Akakievich #" + id);
        return fakeUser;
    }
}
