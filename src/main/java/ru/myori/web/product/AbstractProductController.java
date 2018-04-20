package ru.myori.web.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.myori.AuthorizedUser;
import ru.myori.model.Product;
import ru.myori.service.ProductService;

import java.util.List;


public abstract class AbstractProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

//    private final int userId=100000;
    @Autowired
    private ProductService service;

    public Product get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get product {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete product {} by User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Product> getAll(){
        int userId = AuthorizedUser.id();
        log.info("get all products for User {}", userId);
        return  service.getAll();
    }

    public Product create(Product product) {
        int userId = AuthorizedUser.id();
//        checkNew(meal);
        log.info("create {} by User {}", product, userId);
        return service.create(product);
    }

    public void update(Product product) {
        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} by User {}", product, userId);
        service.update(product);
    }

}