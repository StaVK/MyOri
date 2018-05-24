package ru.myori.repository.order;

import ru.myori.model.Order;
import ru.myori.model.Product;
import ru.myori.model.User;

import java.util.List;

public interface OrderRepository {

    Order save(Order order, int userId);

    Order get(int id, int userId);

    List<Order> getAll(int userId);

    List<Order> getAllActive(int userId, int customerId, int status);

    boolean delete(int id, int userId);

    User chgCustomer(int customerId, int orderId, int userId);

    void changeStatus(int orderId, int status);
}
