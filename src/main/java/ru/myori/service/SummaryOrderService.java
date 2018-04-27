package ru.myori.service;

import ru.myori.model.OrderProduct;
import ru.myori.model.SummaryOrder;

import java.util.List;

public interface SummaryOrderService {
    List<SummaryOrder> getAll();
}
