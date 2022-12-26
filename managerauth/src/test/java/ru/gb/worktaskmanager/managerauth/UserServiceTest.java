package ru.gb.worktaskmanager.managerauth;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.worktaskmanager.managerauth.entities.Users;
import ru.gb.worktaskmanager.managerauth.services.UserService;

import java.util.Optional;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    @MockBean
    private UserService userService;

    @Test
    public void findByUserNameTest() {
        Users user = new Users();
        user.setId(1L);
        user.setUsername("bob");
        user.setName("bob");
        Mockito.doReturn(Optional.of(user))
                .when(userService)
                .findByUsername("bob");
        userService.findByUsername("bob");
        Mockito.verify(userService, Mockito.times(1)).findByUsername(ArgumentMatchers.any());
    }
}
