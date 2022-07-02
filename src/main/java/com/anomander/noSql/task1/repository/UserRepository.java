package com.anomander.noSql.task1.repository;

import com.anomander.noSql.task1.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByName(String name);

}
