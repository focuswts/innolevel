package com.innolevel.controller;

import com.innolevel.entity.User;
import com.innolevel.repository.UserRepository;
import com.innolevel.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
@Api(value = "users", description = "Endpoint for user management")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Optional<List<User>> getUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/create")
    public Optional<User> createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @PutMapping("/update")
    public Optional<User> updateUser(@RequestBody final User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable final int id) {
       return userService.deleteUser(id);
    }


}
