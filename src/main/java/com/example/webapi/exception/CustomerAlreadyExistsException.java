package com.example.webapi.exception;

import com.example.webapi.dao.Customer;

public class CustomerAlreadyExistsException extends Exception{
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
