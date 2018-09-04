package ru.myori.service;

import ru.myori.model.Customer;
import ru.myori.model.People;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll(int userId);

    boolean delete(int customerId);

    Customer create(People people, int userId);

    Customer get(int customerId);
}
