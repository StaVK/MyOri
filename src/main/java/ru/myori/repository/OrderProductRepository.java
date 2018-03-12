package ru.myori.repository;

import ru.myori.model.OrderProduct;

import java.util.Set;

public interface OrderProductRepository {
    OrderProduct save(OrderProduct orderProduct);
    Set<OrderProduct> getAll(int orderId);
    OrderProduct get(OrderProduct orderProduct);
    OrderProduct getProd(int orderId, int prodId);
    int update(int orderId, int article, int volume);
    boolean delete(int prodId);
}
