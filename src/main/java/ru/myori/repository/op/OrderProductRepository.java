package ru.myori.repository.op;

import ru.myori.model.OrderProduct;

import java.util.List;
import java.util.Set;

public interface OrderProductRepository {
    OrderProduct save(OrderProduct orderProduct);
    List<OrderProduct> getAll(int orderId);
    List<OrderProduct> getAllForSummary(int userId);
    OrderProduct get(int opId);
    OrderProduct getProd(int orderId, int prodId);

    int update(OrderProduct orderProduct);
    boolean delete(int opId);
}
