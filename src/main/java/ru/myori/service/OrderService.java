package ru.myori.service;

import ru.myori.model.Order;

import java.util.List;

public interface OrderService {

    Order get(int id);

//    boolean addProductInOrder(int orderId, int id);

    List<Order> getAll(int userId);

    Order create(Order order);

    void delete(int id);

    Order update(Order order);
}
