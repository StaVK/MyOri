package ru.myori.to;

import ru.myori.model.OrderProduct;
import ru.myori.model.ReserveProduct;

import java.util.Set;

public class OrderProductTo extends OrderProduct {
//    Set<ReserveProduct> reserve;
    ReserveProduct reserve;

    int available;

//    public OrderProductTo(OrderProduct orderProduct, Set<ReserveProduct> reserve, int available) {
    public OrderProductTo(OrderProduct orderProduct, ReserveProduct reserve, int available) {
        super(orderProduct);
        this.reserve = reserve;
        this.available = available;
    }
}
