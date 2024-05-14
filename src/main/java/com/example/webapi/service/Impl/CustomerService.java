package com.example.webapi.service.Impl;

import com.example.webapi.dao.Customer;
import com.example.webapi.dto.ApiResponseDto;
import com.example.webapi.dto.ApiResponseStatus;
import com.example.webapi.dto.CustomerDto;
import com.example.webapi.exception.CustomerAlreadyExistsException;
import com.example.webapi.exception.CustomerNotFoundException;
import com.example.webapi.exception.CustomerServiceLogicException;
import com.example.webapi.repository.CustomerRepository;
import com.example.webapi.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> addCustomer(CustomerDto newCustomerDetails) throws CustomerAlreadyExistsException, CustomerServiceLogicException {
        try {
            if (customerRepository.findByEmail(newCustomerDetails.getEmail()) != null){
                throw new CustomerAlreadyExistsException("Customer already exists with email " + newCustomerDetails.getEmail());
            }

            Customer newCustomer = new Customer(
                    null,
                    newCustomerDetails.getFirstName(),
                    newCustomerDetails.getLastName(),
                    newCustomerDetails.getEmail(),
                    newCustomerDetails.getPersonalMobile1(),
                    newCustomerDetails.getPersonalMobile2(),
                    newCustomerDetails.getBirthDate(),
                    newCustomerDetails.getRdvPaymentHandler(),
                    newCustomerDetails.getCardHolder(),
                    newCustomerDetails.getCardNumber(),
                    newCustomerDetails.getMonth(),
                    newCustomerDetails.getYear(),
                    newCustomerDetails.getCvv()
            );

            customerRepository.save(newCustomer);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "New customer has been successfully created!"));

        }catch (CustomerAlreadyExistsException e) {
            throw new CustomerAlreadyExistsException(e.getMessage());
        }catch (Exception e) {
            log.error("Failed to create new customer account: " + e.getMessage());
            throw new CustomerServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllCustomers() throws CustomerServiceLogicException {
        try {
            List<Customer> clients = customerRepository.findAll();

            // customers should be mapped to customerDto before sent to the client
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), clients)
                    );

        }catch (Exception e) {
            log.error("Failed to fetch all customers: " + e.getMessage());
            throw new CustomerServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getCustomer(Long id) throws CustomerServiceLogicException,CustomerNotFoundException {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(
                    () -> new CustomerNotFoundException("Customer does not exists")
            );

            // customer should be mapped to customerDto before sent to the client
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), customer));

        }catch (Exception e) {
            log.error("Failed to find user by id: " + e.getMessage());
            throw new CustomerServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateCustomer(CustomerDto newCustomerDetails, Long id) throws CustomerNotFoundException, CustomerServiceLogicException {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id));

            // Update customer with data from newCustomerDetails
            customer.setFirstName(newCustomerDetails.getFirstName());
            customer.setLastName(newCustomerDetails.getLastName());
            customer.setEmail(newCustomerDetails.getEmail());
            customer.setPersonalMobile1(newCustomerDetails.getPersonalMobile1());
            customer.setPersonalMobile2(newCustomerDetails.getPersonalMobile2());
            customer.setBirthDate(newCustomerDetails.getBirthDate());
            customer.setRdvPaymentHandler(newCustomerDetails.getRdvPaymentHandler());
            customer.setCardHolder(newCustomerDetails.getCardHolder());
            customer.setCardNumber(newCustomerDetails.getCardNumber());
            customer.setMonth(newCustomerDetails.getMonth());
            customer.setYear(newCustomerDetails.getYear());
            customer.setCvv(newCustomerDetails.getCvv());

            customerRepository.save(customer);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Customer updated successfully!")
                    );

        }catch(CustomerNotFoundException e){
            throw new CustomerNotFoundException(e.getMessage());
        }catch(Exception e) {
            log.error("Failed to update customer details: " + e.getMessage());
            throw new CustomerServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteCustomer(Long id) throws CustomerServiceLogicException, CustomerNotFoundException {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id));
            customerRepository.delete(customer);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Customer deleted successfully!")
                    );

        }catch(CustomerNotFoundException e){
            throw new CustomerNotFoundException(e.getMessage());
        }catch(Exception e) {
            log.error("Failed to delete customer : " + e.getMessage());
            throw new CustomerServiceLogicException();
        }
    }
}
