package com.example.userservice.service.impl;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, User> userStorage = new HashMap<>();
    private long userIdSequence = 1L;

    @Override
    public User createUser(User user) {
        user.setId(userIdSequence++);
        userStorage.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userStorage.containsKey(id)) {
            user.setId(id);
            userStorage.put(id, user);
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userStorage.remove(id) != null;
    }
}
