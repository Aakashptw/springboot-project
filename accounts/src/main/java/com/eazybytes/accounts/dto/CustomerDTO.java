package com.eazybytes.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {

    @Schema(
            description = "Name of the Customer", example = "Easy Bytes"
    )
    @NotEmpty(message = "Name cannot be a Null or Empty")
    @Size(min = 5, max=30, message = "The length of the Customer Name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email Address of the Customer", example = "aakashptw@gmail.com"
    )
    @NotEmpty(message = "Email Address cannot be a Null or Empty")
    @Email(message = "Email Address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile number of the Customer", example = "8974998475"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account Details of the Customer"
    )
    @JsonProperty("Account")
    private AccountsDTO accountsDTO;
}
