package ru.myori.repository.customerProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.CustomerProduct;

import java.util.HashSet;
import java.util.Set;

@Repository
public class DataJpaCustomerProductRepository implements CustomerProductRepository {

    @Autowired
    CrudCustomerProductRepository crudCustomerProductRepository;

    @Override
    public CustomerProduct save(CustomerProduct customerProduct) {
        return crudCustomerProductRepository.save(customerProduct);
    }

    @Override
    public Set<CustomerProduct> getAll(int customerId) {
        return new HashSet<>(crudCustomerProductRepository.findAll(customerId));
    }
}
