package ru.myori.to;

import ru.myori.model.OrderProduct;
import ru.myori.model.ReserveProduct;

import java.util.Set;

public class OrderProductTo extends OrderProduct {
    Set<ReserveProduct> reserve;

    public OrderProductTo(OrderProduct orderProduct, Set<ReserveProduct> reserve) {
        super(orderProduct);
        this.reserve = reserve;
    }
}
