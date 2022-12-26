package com.beresten.polyclinic.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String s) {
        super(s);
    }
}
