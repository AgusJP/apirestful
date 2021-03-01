package com.school.apirestful.exceptions;

public class AlreadyExistsException extends RuntimeException{

    public AlreadyExistsException(final String message) {
        super(message);
    }
}
