package com.hsbc.service;

import com.hsbc.jpa.entity.Account;
import com.hsbc.jpa.entity.Customer;
import com.hsbc.jpa.repository.AccountRepository;
import com.hsbc.jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer updateCustomer(Customer updatedCustomer) {
        Customer customer = getCustomerById(updatedCustomer.getId());
        customer.setAddress(updatedCustomer.getAddress());

        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.delete(getCustomerById(id));
    }

    public Customer createAccountAndAddToCustomer(Long customerId, Account account) {
        Customer customer = getCustomerById(customerId);
        account.setCustomer(customer);
        customer.getAccounts().add(account);

        return customerRepository.save(customer);
    }

    public Customer deleteAccountByCustomerIdAndAccountId(Long customerId, Long accountId) {
        Customer customer = getCustomerById(customerId);
        Account account = customer.getAccounts().stream()
                .filter(customerAccount -> customerAccount.getId() ==  accountId)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);

        customer.getAccounts().remove(account);
        accountRepository.delete(account);

        return customerRepository.save(customer);
    }

}
