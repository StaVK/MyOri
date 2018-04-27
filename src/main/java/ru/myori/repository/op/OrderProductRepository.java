package ru.myori.repository.op;

import ru.myori.model.OrderProduct;

import java.util.List;
import java.util.Set;

public interface OrderProductRepository {
    OrderProduct save(OrderProduct orderProduct);
    List<OrderProduct> getAll(int orderId);
    List<OrderProduct> getAll();
    OrderProduct get(OrderProduct orderProduct);
    OrderProduct getProd(int orderId, int prodId);
    int update(int orderId, int article, int volume);
    boolean delete(int prodId);
}
