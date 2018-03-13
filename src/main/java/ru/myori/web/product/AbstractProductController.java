package ru.myori.web.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.myori.model.Product;
import ru.myori.service.ProductService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;


public abstract class AbstractProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int userId=100000;
    @Autowired
    private ProductService service;

    public Product get(int id) {
//        int userId = AuthorizedUser.id();
        log.info("get product {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
//        int userId = AuthorizedUser.id();
        log.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Product> getAll(){
        log.info("get all products for User {}", userId);
        return  service.getAll();
    }

/*    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for User {}", userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }*/

    public Product create(Product product) {
//        int userId = AuthorizedUser.id();
//        checkNew(meal);
        log.info("create {} for User {}", product, userId);
        return service.create(product);
    }

    public void update(Product product) {
//        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} for User {}", product, userId);
        service.update(product);
    }

}