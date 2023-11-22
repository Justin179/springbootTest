package com.justin.springboottest.javabestpractices.exceptions.catchblock;

class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }
}
