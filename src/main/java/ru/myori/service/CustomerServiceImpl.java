package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.myori.model.Customer;
import ru.myori.model.People;
import ru.myori.model.User;
import ru.myori.repository.customer.CustomerRepository;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserService userService;

    @Autowired
    PeopleService peopleService;

    @Override
    public List<Customer> getAll(int userId) {
        return customerRepository.getAll(userId);
    }

    @Override
    public boolean delete(int customerId) {
        return customerRepository.delete(customerId);
    }

    @Transactional
    @Override
    public Customer create(People people, int userId) {
        Assert.notNull(people, "user must not be null");
        //        Assert.isNull(getByEmail(customer.getEmail()),"Customer with this email already exists!");

        User user = userService.get(userId);
        peopleService.save(people);
        Customer customer = new Customer(people, user);
        return customerRepository.save(customer);
    }

    @Override
    public int update(People people) {
/*        Customer customer = updateFromTo(get(userTo.getId()), userTo);
        Assert.isNull(getByEmail(user.getEmail()), "User with this email already exists!");
        userRepository.save(prepareToSave(user));*/
        return 0;
    }

    @Override
    public Customer get(int customerId) {
        return customerRepository.get(customerId);
    }
}
