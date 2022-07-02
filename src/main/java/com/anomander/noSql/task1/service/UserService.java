package com.anomander.noSql.task1.service;

import com.anomander.noSql.task1.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(long userId);

    List<User> getAllUsers();

}
