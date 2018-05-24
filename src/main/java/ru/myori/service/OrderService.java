package ru.myori.service;

import ru.myori.model.Order;
import ru.myori.model.User;

import java.util.List;

public interface OrderService {

    Order get(int id, int userId);

//    boolean addProductInOrder(int orderId, int id);

    List<Order> getAll(int userId);

    Order create(Order order, int userId);

    void delete(int id, int userId);

    Order update(Order order, int userId);

    User chgCustomer(int customerId, int orderId, int userId); //TODO доделать изменение покупателя в заказе

    void changeStatus(int orderId, int status);
}
