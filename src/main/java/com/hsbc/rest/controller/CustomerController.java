package com.hsbc.rest.controller;

import com.hsbc.jpa.entity.Customer;
import com.hsbc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerService.createCustomer(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

    @GetMapping("/customers")
    List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        return customerService.updateCustomer(customer);
    }

}
