package com.example.webapi.service;

import com.example.webapi.dto.ApiResponseDto;
import com.example.webapi.dto.CustomerDto;
import com.example.webapi.exception.CustomerAlreadyExistsException;
import com.example.webapi.exception.CustomerNotFoundException;
import com.example.webapi.exception.CustomerServiceLogicException;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {
    ResponseEntity<ApiResponseDto<?>> addCustomer(CustomerDto newCustomerDetails)
            throws CustomerAlreadyExistsException, CustomerServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> getAllCustomers()
            throws CustomerServiceLogicException;


    ResponseEntity<ApiResponseDto<?>> getCustomer(Long id) throws CustomerServiceLogicException, CustomerNotFoundException;

    ResponseEntity<ApiResponseDto<?>> updateCustomer(CustomerDto newCustomerDetails, Long id)
            throws CustomerNotFoundException, CustomerServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> deleteCustomer(Long id)
            throws CustomerServiceLogicException, CustomerNotFoundException;
}

