package com.example.SwiggyClone.exception;

public class DuplicateEntityException extends RuntimeException{

    public DuplicateEntityException(String message){
        super(message);
    }
}
