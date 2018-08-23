package ru.myori.web.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Customer;
import ru.myori.model.People;
import ru.myori.service.CustomerService;

import java.util.List;
import java.util.Set;

public class AbstractCustomerController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerService customerService;

    public List<Customer> getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll customers for User {}", userId);
        return customerService.getAll(userId);
    }

    public Customer create(People people){
        int userId = AuthorizedUser.id();
        log.info("create customer for User {}", userId);
        return customerService.create(people, userId);
    }

    public int update(People people){
        int userId = AuthorizedUser.id();
        log.info("update customer for User {}", userId);
        return customerService.update(people);
    }
}
