package com.anomander.noSql.task1.service.impl;

import com.anomander.noSql.task1.exception.GlobalApplicationException;
import com.anomander.noSql.task1.exception.UserNotFoundException;
import com.anomander.noSql.task1.repository.UserRepository;
import com.anomander.noSql.task1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.anomander.noSql.task1.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long userId) {
        log.info("getting user by id  " + userId);

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found by id " + userId));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("getting user by email " + email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found by email " + email));
    }

    @Override
    public List<User> getUsersByName(String name) {
        log.info("getting users by name " + name);
        return userRepository.findAllByName(name);
    }

    @Override
    public User createUser(User user) {
        log.info("creating user:  " + user);

        if (userIsNotUnique(user)) {
            throw new GlobalApplicationException("User with such email is already present");
        }
        return userRepository.save(user);
    }

    private boolean userIsNotUnique(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        log.info("updating user " + user);

        User userById = getUserById(user.getId());

        if (userIsNotUnique(user) && !userById.getEmail().equals(user.getEmail())) {
            throw new GlobalApplicationException("User with such email is already present");
        }
        return userById
                .setEmail(user.getEmail())
                .setName(user.getName());
    }

    @Override
    public void deleteUser(long userId) {
        log.info("deleting user by id " + userId);
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        log.info("getting all users");
        return (List<User>) userRepository.findAll();
    }
}