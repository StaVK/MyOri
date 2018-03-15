package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.Order;
import ru.myori.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

/*    @Override
    public boolean addProductInOrder(int orderId, int id) {
        return orderRepository.addProductInOrder(orderId,id);
    }*/

    @Override
    public Order get(int id) {
        return orderRepository.get(id);
    }

    @Override
    public List<Order> getAll(int userId) {
        return orderRepository.getAll(userId);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }
}
