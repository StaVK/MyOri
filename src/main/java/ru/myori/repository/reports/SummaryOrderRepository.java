package ru.myori.repository.reports;

import ru.myori.model.OrderProduct;
import ru.myori.model.SummaryOrder;

import java.util.List;

public interface SummaryOrderRepository {
    List<SummaryOrder> getAll();
}
