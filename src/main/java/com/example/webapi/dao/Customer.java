package com.example.webapi.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
