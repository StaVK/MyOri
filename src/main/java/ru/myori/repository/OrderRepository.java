package ru.myori.repository;

import ru.myori.model.Order;
import ru.myori.model.Product;

import java.util.List;

public interface OrderRepository {

    Order save(Order order, int userId);

    Order get(int id, int userId);

    List<Order> getAll(int userId);

    boolean delete(int id, int userId);
}
