package com.anomander.noSql.task1.repository;

import com.anomander.noSql.task1.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserAccountRepository extends MongoRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserId(long userId);
}
