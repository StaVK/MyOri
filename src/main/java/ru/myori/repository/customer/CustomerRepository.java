package ru.myori.repository.customer;

import ru.myori.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerRepository {
    // null if not found
    Customer get(int customerId);

    List<Customer> getAll(int userId);

    Customer save(Customer user);

    // false if not found
    boolean delete(int customerId);

}
