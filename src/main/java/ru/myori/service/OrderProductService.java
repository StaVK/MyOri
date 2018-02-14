package ru.myori.service;

import ru.myori.model.OrderProduct;

import java.util.Set;

public interface OrderProductService {

    OrderProduct create(OrderProduct orderProduct);

    Set<OrderProduct> getAll();
}
