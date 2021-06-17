package com.innolevel.service;

import com.innolevel.entity.User;
import com.innolevel.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<List<User>> findAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    public Optional<User> createUser(final User user) {
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> updateUser(final User user) {
        Optional<User> dbUser = userRepository.findById(user.getId());

        if (!dbUser.get().equals(user)) {
            userRepository.save(user);
        }

        return Optional.of(user);
    }

    public HttpStatus deleteUser(final int id) {
        try {
            userRepository.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
