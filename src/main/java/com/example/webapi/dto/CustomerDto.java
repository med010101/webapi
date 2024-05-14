package com.example.webapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private Integer personalMobile1;
    private Integer personalMobile2;
    private Date birthDate;
    private String rdvPaymentHandler;
    private String cardHolder;
    private Integer cardNumber;
    private Integer month;
    private Integer year;
    private Integer cvv;
}
