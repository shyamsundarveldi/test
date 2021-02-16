package com.hsbc.service;

import com.hsbc.jpa.entity.Account;
import com.hsbc.jpa.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerService customerService;

    @Transactional(readOnly = true)
    public List<Account> getAllAccountByCustomerId(Long customerId) {
        return customerService.getCustomerById(customerId).getAccounts();
    }

    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public long getBalanceByAccountId(Long id) {
        Account account = getAccountById(id);
        return account.getBalance();

    }

    public Account updateAccount(Account updatedAccount) {
        Account account = getAccountById(updatedAccount.getId());
        account.setBalance(updatedAccount.getBalance());
        account.setTransactions(updatedAccount.getTransactions());

        return accountRepository.save(account);
    }

}
