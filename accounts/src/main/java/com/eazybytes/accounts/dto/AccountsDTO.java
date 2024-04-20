package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDTO {

    @Schema(
            description = "Account number for easy bank account", example = "3728456703"
    )
    @NotEmpty(message = "Account Number cannot be a Null or Empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be of 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of easy bank account", example = "Savings"
    )
    @NotEmpty(message = "Account Type cannot be a Null or Empty")
    private String accountType;

    @Schema(
            description = "Easy bank branch address", example = "123, New York"
    )
    @NotEmpty(message = "branch Address cannot be a Null or Empty")
    private String branchAddress;
}
