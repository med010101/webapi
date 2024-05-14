package com.example.webapi.exception;

public class CustomerServiceLogicException extends Exception{
    public CustomerServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}
