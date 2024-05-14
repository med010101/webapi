package com.example.webapi.controller;

import com.example.webapi.dto.ApiResponseDto;
import com.example.webapi.dto.CustomerDto;
import com.example.webapi.exception.CustomerAlreadyExistsException;
import com.example.webapi.exception.CustomerNotFoundException;
import com.example.webapi.exception.CustomerServiceLogicException;
import com.example.webapi.service.Impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> addCustomer(@RequestBody CustomerDto customerDto) throws CustomerAlreadyExistsException, CustomerServiceLogicException {
        return customerService.addCustomer(customerDto);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseDto<?>> getAllCustomers() throws CustomerServiceLogicException {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> getCustomer(@PathVariable Long id) throws CustomerServiceLogicException, CustomerNotFoundException {
        return customerService.getCustomer(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<?>> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto)
            throws CustomerNotFoundException, CustomerServiceLogicException {
        return customerService.updateCustomer(customerDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteCustomer(@PathVariable Long id)
            throws CustomerNotFoundException, CustomerServiceLogicException {
        return customerService.deleteCustomer(id);
    }


}
