package com.victor.handler;

public class NameNotFoundException extends RuntimeException{
    public NameNotFoundException(String message) {
        super(message);
    }
}
