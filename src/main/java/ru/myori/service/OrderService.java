package ru.myori.service;

import ru.myori.model.Order;

import java.util.List;

public interface OrderService {

    Order get(int id, int userId);

//    boolean addProductInOrder(int orderId, int id);

    List<Order> getAll(int userId);

    Order create(Order order, int userId);

    void delete(int id, int userId);

    Order update(Order order, int userId);
}
