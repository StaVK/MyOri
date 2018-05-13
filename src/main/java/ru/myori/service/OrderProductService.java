package ru.myori.service;

import ru.myori.model.Order;
import ru.myori.model.OrderProduct;

import java.util.List;
import java.util.Set;

public interface OrderProductService {

    OrderProduct create(OrderProduct orderProduct);

    List<OrderProduct> getAll(int orderId);

    List<OrderProduct> getAllForSummary(int userId);

    OrderProduct get(OrderProduct orderProduct);

    OrderProduct getProd(int orderId, int prodId);

    int update(int orderId, int article, int volume);

    boolean delete(int prodId);
}
