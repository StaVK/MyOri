package ru.myori.web.customerProduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.CustomerProduct;
import ru.myori.service.CustomerProductService;

import java.util.Set;

public class AbstractCustomerProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomerProductService customerProductService;

    public Set<CustomerProduct> getAll(int customerId){
        int userId = AuthorizedUser.id();
        log.info("get all customer products for {} for User {}", customerId, userId);
        return customerProductService.getAll(customerId);
    }
}
