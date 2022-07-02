package com.anomander.noSql.task1.service;

import com.anomander.noSql.task1.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService {

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount getUserAccountById(long id);

    UserAccount getUserAccountByUserId(long userId);

    void topUpUserAccount(long userId, BigDecimal money);

    void withdrawMoneyFromAccount(long userId, BigDecimal money);

}
