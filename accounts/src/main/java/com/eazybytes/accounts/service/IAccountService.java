package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDTO;

public interface IAccountService {

    /*@param customerDTO - CustomerDTO Object*/
    void createAccount(CustomerDTO customerDTO);

/*
    @param mobileNumber - Input Mobile Number
    @return Account Details based on a given mobile number
     */

    CustomerDTO fetchAccount(String mobileNumber);
}
