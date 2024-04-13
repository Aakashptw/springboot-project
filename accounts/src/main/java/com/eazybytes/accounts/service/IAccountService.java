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

    /*
    @param customerDTO - CustomerDTO Object
    @return boolean indicating if the update of Account details is successful or not
    */
    boolean updateAccount(CustomerDTO customerDTO);

   /*
    @param mobileNumber - Input Mobile Number
    @return boolean indicating if the delete of Account details is successful or not

    */

    boolean deleteAccount(String mobileNumber);
}
