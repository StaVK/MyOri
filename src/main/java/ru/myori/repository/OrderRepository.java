package ru.myori.repository;

import ru.myori.model.Order;
import ru.myori.model.Product;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    Order get(int id);

    List<Order> getAll();

    boolean delete(int id);
}
