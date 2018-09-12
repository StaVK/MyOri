package ru.myori.to;

import ru.myori.model.OrderProduct;
import ru.myori.model.ReserveProduct;

import java.util.Set;

public class OrderProductTo extends OrderProduct {

//    ReserveProduct reserve;

    int reserve;

    int available;

    public OrderProductTo(OrderProduct orderProduct, int reserve, int available) {
        super(orderProduct);
        this.reserve = reserve;
        this.available = available;
    }
}
