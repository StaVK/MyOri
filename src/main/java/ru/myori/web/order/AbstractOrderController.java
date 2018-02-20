package ru.myori.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.model.Order;
import ru.myori.model.User;
import ru.myori.service.OrderService;
import ru.myori.service.UserService;


public abstract class AbstractOrderController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int userId=100000;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    public User getUser(int userId){
        return userService.get(userId);
    }

    public Order get(int id) {
//        int userId = AuthorizedUser.id();
        log.info("get order {} for User {}", id, userId);
        return orderService.get(id);
    }

    public void delete(int id) {
//        int userId = AuthorizedUser.id();
        log.info("delete order {} for User {}", id, userId);
        orderService.delete(id);
    }

    public Order create(Order order) {
//        int userId = AuthorizedUser.id();
//        checkNew(meal);
        log.info("create {} for User {}", order, userId);
        return orderService.create(order);
    }

    public void update(Order order, int id) {
//        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} for User {}", order, userId);
//        service.update(product, userId);
    }

}