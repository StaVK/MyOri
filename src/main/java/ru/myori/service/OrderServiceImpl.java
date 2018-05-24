package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.Order;
import ru.myori.model.User;
import ru.myori.repository.order.OrderRepository;

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
    public Order get(int id, int userId) {
        return orderRepository.get(id, userId);
    }

    @Override
    public List<Order> getAll(int userId) {
        return orderRepository.getAll(userId);
    }

    @Override
    public Order create(Order order, int userId) {
        return orderRepository.save(order, userId);
    }

    @Override
    public void delete(int id, int userId) {
        orderRepository.delete(id, userId);
    }

    @Override
    public Order update(Order order, int userId) {
        return orderRepository.save(order, userId);
    }

    @Override
    public User chgCustomer(int customerId, int orderId, int userId) {
        return orderRepository.chgCustomer(customerId, orderId, userId);
    }

    @Override
    public void changeStatus(int orderId, int status) {
        orderRepository.changeStatus(orderId, status);
    }
}
