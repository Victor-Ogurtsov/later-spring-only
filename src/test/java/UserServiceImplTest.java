
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.user.User;
import ru.practicum.user.UserService;
import ru.practicum.user.dto.UserRequestDto;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Transactional
@SpringBootTest(
        properties = "jdbc.url=jdbc:postgresql://localhost:6532/latertest",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserServiceImplTest {

    private final EntityManager em;
    private final UserService service;

    @Test
    void testSaveUser() {
        UserRequestDto userRequestDto = makeUserDto("some@email.com", "Пётр", "Иванов");

        service.saveUser(userRequestDto);

        TypedQuery<User> query = em.createQuery("Select u from User u where u.email = :email", User.class);
        User user = query.setParameter("email", userRequestDto.getEmail())
                .getSingleResult();

        assertThat(user.getId(), notNullValue());
        assertThat(user.getFirstName(), equalTo(userRequestDto.getFirstName()));
        assertThat(user.getLastName(), equalTo(userRequestDto.getLastName()));
        assertThat(user.getEmail(), equalTo(userRequestDto.getEmail()));
        assertThat(user.getRegistrationDate(), notNullValue());
    }

    private UserRequestDto makeUserDto(String email, String firstName, String lastName) {
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail(email);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        return dto;
    }
}
