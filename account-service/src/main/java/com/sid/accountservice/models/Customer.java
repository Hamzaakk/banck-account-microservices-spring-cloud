package com.sid.accountservice.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;

}
