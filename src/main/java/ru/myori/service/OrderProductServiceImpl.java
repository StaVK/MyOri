package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.OrderProduct;
import ru.myori.repository.op.OrderProductRepository;

import java.util.List;
import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public boolean delete(int prodId) {
        return orderProductRepository.delete(prodId);
    }

    @Override
    public List<OrderProduct> getAll(int orderId) {
        return orderProductRepository.getAll(orderId);
    }

    @Override
    public OrderProduct get(OrderProduct orderProduct) {
        return orderProductRepository.get(orderProduct);
    }

    @Override
    public OrderProduct getProd(int orderId, int prodId) {
        return orderProductRepository.getProd(orderId, prodId);
    }

    @Override
    public int update(int orderId, int article, int volume) {
        return orderProductRepository.update(orderId, article, volume);
    }
}
