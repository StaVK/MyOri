package ru.myori.repository.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.Customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DataJpaCustomerRepository implements CustomerRepository {

    @Autowired
    private CrudCustomerRepository crudCustomerRepository;

    @Override
    public Customer get(int customerId){
        return crudCustomerRepository.findOne(customerId);
    }

    @Override
    public List<Customer> getAll(int userId) {
        return crudCustomerRepository.findAllByUserId(userId);
    }

    @Override
    public Customer save(Customer user) {
        return crudCustomerRepository.save(user);
    }

    @Override
    public boolean delete(int customerId) {
        return crudCustomerRepository.delete(customerId)!=0;
    }
}
