package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {

    @NotEmpty(message = "Account Number cannot be a Null or Empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be of 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be a Null or Empty")
    private String accountType;

    @NotEmpty(message = "branch Address cannot be a Null or Empty")
    private String branchAddress;
}
