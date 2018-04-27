package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.OrderProduct;
import ru.myori.model.SummaryOrder;
import ru.myori.repository.reports.SummaryOrderRepository;

import java.util.List;

//@Service
public class SummaryOrderServiceImpl implements SummaryOrderService{

//    @Autowired
    private SummaryOrderRepository summaryOrderRepository;

    public List<SummaryOrder> getAll(){
        return summaryOrderRepository.getAll();
    }
}
