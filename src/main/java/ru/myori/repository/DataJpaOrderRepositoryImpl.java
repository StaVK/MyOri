package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataJpaOrderRepositoryImpl implements OrderRepository {

    @Autowired
    private CrudOrderRepository crudOrderRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Order save(Order order, int userId) {
        if (!order.isNew() && get(order.getOrderId(), userId) == null) {
            return null;
        }
        order.setUser(crudUserRepository.findOne(userId));
        return crudOrderRepository.save(order);
    }

    @Override
    public Order get(int id, int userId) {
//        return crudOrderRepository.findOne(id);
        return crudOrderRepository.getOrderByOrderIdAndUserId(id,userId);
    }

    @Override
    public List<Order> getAll(int userId) {
        return crudOrderRepository.getAll(userId);
    }

    @Modifying
    @Transactional
    public boolean delete(int id, int userId) {
        crudOrderRepository.delete(id, userId);
        return true;//TODO Исправить
    }
}
