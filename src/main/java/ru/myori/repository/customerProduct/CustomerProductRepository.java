package ru.myori.repository.customerProduct;

import ru.myori.model.CustomerProduct;

import java.util.List;
import java.util.Set;

public interface CustomerProductRepository {
    CustomerProduct save(CustomerProduct customerProduct);
    Set<CustomerProduct> getAll(int customerId);
}
