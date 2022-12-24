package com.beresten.polyclinic.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String s) {
        super(s);
    }

}
