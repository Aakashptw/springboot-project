package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {

        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given Mobile Number "
            + customerDTO.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }



    /*
    @param customer - Customer Object
    @return the new account details
    */
    private Accounts createNewAccount(Customer customer){
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(AccountConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountConstants.ADDRESS);
        return newAccounts;
    }

/*
    @param mobileNumber - Input Mobile Number
    @return Account Details based on a given mobile number
     */

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
        );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","customerId", customer.getCustomerId().toString())
        );
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if(accountsDTO != null){
            Accounts accounts = accountRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDTO, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException(
                            "Customer","CustomerID", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

     /*
    @param mobileNumber - Input Mobile Number
    @return boolean indicating if the delete of Account details is successful or not

    */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
