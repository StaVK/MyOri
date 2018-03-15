package ru.myori.service;

import ru.myori.model.Order;
import ru.myori.model.OrderProduct;

import java.util.Set;

public interface OrderProductService {

    OrderProduct create(OrderProduct orderProduct);

    Set<OrderProduct> getAll(int orderId);

    OrderProduct get(OrderProduct orderProduct);

    OrderProduct getProd(int orderId, int prodId);

    int update(int orderId, int article, int volume);

    boolean delete(int prodId);
}