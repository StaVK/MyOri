package ru.myori.service;

import ru.myori.model.CustomerProduct;

import java.util.Set;

public interface CustomerProductService {

    Set<CustomerProduct> getAll(int customerId);
}
