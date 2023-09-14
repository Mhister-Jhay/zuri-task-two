package org.jhay.common.exception.exceptions;

public class PersonAlreadyExistException extends RuntimeException{
    public PersonAlreadyExistException(String message) {
        super(message);
    }
}
