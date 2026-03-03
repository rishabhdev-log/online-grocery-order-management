package com.plays.grocery.service;

import com.plays.grocery.model.Customer;
import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);

    List<Customer> getAll();

    Customer getById(Long id);

    Customer update(Long id, Customer customer);

    void delete(Long id);
}