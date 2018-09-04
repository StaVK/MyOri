package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.CustomerProduct;
import ru.myori.repository.customerProduct.CustomerProductRepository;

import java.util.Set;

@Service
public class CustomerProductServiceImpl implements CustomerProductService {

    @Autowired
    CustomerProductRepository customerProductRepository;

    @Override
    public Set<CustomerProduct> getAll(int customerId){
        return customerProductRepository.getAll(customerId);
    }
}
