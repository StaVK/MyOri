package ru.myori.repository;

import ru.myori.model.OrderProduct;

import java.util.Set;

public interface OrderProductRepository {
    OrderProduct save(OrderProduct orderProduct);
    Set<OrderProduct> getAll();
}
