package ru.myori.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.myori.AuthorizedUser;
import ru.myori.model.*;
import ru.myori.to.OrderProductTo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class AbstractOrderController extends AbstractController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public User getUser(int userId) {
        return userService.get(userId);
    }

    public Customer getCustomer(int customerId) {
        return customerService.get(customerId);
    }

    public List<Order> getAll() {
        int userId = AuthorizedUser.id();
        log.info("get all orders for User {}", userId);
        return orderService.getAll(userId);
    }

    public List<OrderProductTo> getAllOP(int orderId) {
        int userId = AuthorizedUser.id();
        log.info("get all OrderProductTo for order {} and User {}", orderId, userId);
        return super.orderProductService.getAll(orderId, userId);
    }

    public Order get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get order {} for User {}", id, userId);
        return orderService.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete order {} for User {}", id, userId);
        orderService.delete(id, userId);
    }

    public void create(int customerId) {
        int userId = AuthorizedUser.id();

        Order order = new Order();
        order.setProducts(new HashSet<>());
        order.setUser(getUser(userId));
        order.setCustomer(getCustomer(customerId));

        log.info("create {} for User {}", order, userId);
        orderService.create(order, userId);
    }

    public void update(Order order, int id) {
        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} for User {}", order, userId);
//        service.update(product, userId);
    }

    public User chgCustomer(int customerId, int orderId) {
        int userId = AuthorizedUser.id();
        log.info("User {} update customer {} for Order {}", userId, customerId, orderId);
        return orderService.chgCustomer(customerId, orderId, userId);
    }

    public void changeStatus(int orderId, int status) {
        int userId = AuthorizedUser.id();
        log.info("User {} update status {} for Order {}", userId, status, orderId);
        orderService.changeStatus(orderId, status);
    }
}