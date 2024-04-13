package com.eazybytes.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private String name;

    private String email;

    private String mobileNumber;

    @JsonProperty("Account")
    private AccountsDTO accountsDTO;
}
