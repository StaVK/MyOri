package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.OrderProduct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DataJpaOrderProductRepositoryImpl implements OrderProductRepository {

    @Autowired
    private CrudOrderProductRepository crudOrderProductRepository;

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        return crudOrderProductRepository.save(orderProduct);
    }

    @Override
    public Set<OrderProduct> getAll() {
        return new HashSet<>(crudOrderProductRepository.findAll());
    }
}
