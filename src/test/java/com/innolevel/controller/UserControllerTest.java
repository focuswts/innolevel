package com.innolevel.controller;

import com.innolevel.entity.User;
import com.innolevel.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.innolevel.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        this.userController = new UserController(userService);
    }


    @Test
    public void createUser() {

        final User user = new User();
        Mockito.when(userService.createUser(user)).thenReturn(Optional.of(createUserMock()));

        final Optional<User> response = userController.createUser(user);

        Assertions.assertThat(response.get()).isNotEqualTo(user);
    }

    @Test
    public void updateUser() {

        final User user = new User();
        user.setUsername("test");
        user.setPassword("testpass");
        Mockito.when(userService.updateUser(user)).thenReturn(Optional.of(createUserMock()));

        final Optional<User> response = userController.updateUser(user);

        Assertions.assertThat(response.get().getUsername()).isEqualTo("focuswts");
        Assertions.assertThat(response.get().getPassword()).isEqualTo("1337");
    }

    @Test
    public void getUsers() {

        Mockito.when(userService.findAllUsers()).thenReturn(createUserListMock());

        final List<User> response = userController.getUsers().get();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.size()).isEqualTo(2);

    }

    @Test
    public void deleteUser(){

        Mockito.when(userService.deleteUser(1)).thenReturn(HttpStatus.OK);

        final HttpStatus status = userController.deleteUser(1);

        Assertions.assertThat(status).isEqualTo(HttpStatus.OK);

    }

    private Optional<List<User>> createUserListMock() {
        List<User> list = new ArrayList<>();
        list.add(createUserMock());
        list.add(createUserMock());
        return Optional.of(list);
    }

    private User createUserMock() {
        User user = new User();
        user.setUsername("focuswts");
        user.setPassword("1337");
        return user;
    }

}
