package ru.myori.repository;

import ru.myori.model.OrderProduct;

import java.util.Set;

public interface OrderProductRepository {
    OrderProduct save(OrderProduct orderProduct);
    Set<OrderProduct> getAll();
    OrderProduct get(OrderProduct orderProduct);
    OrderProduct getProd(int orderId, int prodId);
    int update(OrderProduct orderProduct);
    boolean delete(int prodId);
}
