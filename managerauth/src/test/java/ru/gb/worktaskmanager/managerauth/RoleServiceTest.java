package ru.gb.worktaskmanager.managerauth;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.worktaskmanager.managerauth.entities.Roles;
import ru.gb.worktaskmanager.managerauth.repositories.RoleRepository;
import ru.gb.worktaskmanager.managerauth.services.RoleService;

import java.util.Optional;

@SpringBootTest(classes = RoleService.class)
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void findByNameTest() {
        Roles role = new Roles();
        role.setCode(1L);
        role.setTitle("ADMIN");
        Mockito.doReturn(Optional.of(role))
                .when(roleRepository)
                .findByTitle("ADMIN");
        roleService.getUserRole();
        Mockito.verify(roleRepository, Mockito.times(1)).findByTitle(ArgumentMatchers.any());

    }
}
