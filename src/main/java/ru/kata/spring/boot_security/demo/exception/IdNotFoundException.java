package ru.kata.spring.boot_security.demo.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }
}
