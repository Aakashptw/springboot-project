package com.eazybytes.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Name cannot be a Null or Empty")
    @Size(min = 5, max=30, message = "The length of the Customer Name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email Address cannot be a Null or Empty")
    @Email(message = "Email Address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
    private String mobileNumber;

    @JsonProperty("Account")
    private AccountsDTO accountsDTO;
}
