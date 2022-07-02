package com.anomander.noSql.task1.exception;

public class UserAccountNotFoundException extends RuntimeException {

    public UserAccountNotFoundException(String message) {
        super(message);
    }
}
