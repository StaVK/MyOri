package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Order save(Order order) {

/*        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }*/


        return crudOrderRepository.save(order);
    }

    @Override
    public Order get(int id) {
        return crudOrderRepository.findOne(id);
    }

    @Override
    public List<Order> getAll() {
        return crudOrderRepository.getAll();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
