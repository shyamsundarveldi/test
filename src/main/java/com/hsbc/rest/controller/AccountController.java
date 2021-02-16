package com.hsbc.rest.controller;

import com.hsbc.jpa.entity.Account;
import com.hsbc.jpa.entity.Customer;
import com.hsbc.service.AccountService;
import com.hsbc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/customers/{customerId}/accounts")
    List<Account> getAllAccountsByCustomerId(@PathVariable Long customerId) {
        return accountService.getAllAccountByCustomerId(customerId);
    }

    @GetMapping("/accounts/{id}")
    Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    Account updateAccount(@RequestBody Account account, @PathVariable Long id) {
        return accountService.updateAccount(account);
    }

    @PostMapping("/customers/{customerId}/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createAccountAndAddToCustomer(@PathVariable Long customerId, Account account) {
        return customerService.createAccountAndAddToCustomer(customerId, account);
    }

    @DeleteMapping("/customers/{customerId}/accounts/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountByCustomerIdAndAccountId(@PathVariable Long customerId, @PathVariable Long accountId) {
        customerService.deleteAccountByCustomerIdAndAccountId(customerId, accountId);
    }

    @GetMapping("/accounts/{id}/balance")
    Long getAccountBalanceById(@PathVariable Long id) {
        return accountService.getBalanceByAccountId(id);
    }
}
