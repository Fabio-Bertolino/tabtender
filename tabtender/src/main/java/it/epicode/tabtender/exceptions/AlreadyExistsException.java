package it.epicode.tabtender.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException() {
    }
    public AlreadyExistsException(String message) {
        super(message);
    }
}
