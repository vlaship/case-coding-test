package com.cisco.test.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> aClass, Integer id) {
        super("Not found " + aClass.getSimpleName() + " with ID " + id);
    }
}
