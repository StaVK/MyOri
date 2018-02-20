package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DataJpaOrderProductRepositoryImpl implements OrderProductRepository {

    @Autowired
    private CrudOrderProductRepository crudOrderProductRepository;

    @Autowired
    private CrudOrderRepository crudOrderRepository;

    @Autowired
    private CrudProductRepository crudProductRepository;

    @Override
    public boolean delete(int prodId) {
        return crudOrderProductRepository.delete(prodId)!=0;
    }

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        if(orderProduct.getId()!=null && get(orderProduct).getId()!=null){
            update(orderProduct);
            return get(orderProduct);
        }
        return crudOrderProductRepository.save(orderProduct);
    }

    @Override
    public Set<OrderProduct> getAll() {
        return new HashSet<>(crudOrderProductRepository.findAll());
    }

    @Override
    public OrderProduct get(OrderProduct orderProduct) {
        return crudOrderProductRepository.findOne(orderProduct.getId());
    }

    @Override
    public OrderProduct getProd(int orderId, int prodId) {
        return crudOrderProductRepository.getProd(orderId, prodId);
    }

    @Override
    public int update(OrderProduct orderProduct) {
        return crudOrderProductRepository.update(
                crudOrderRepository.findOne(orderProduct.getOrder().getId()),
                crudProductRepository.findOne(orderProduct.getProduct().getId()),
                orderProduct.getVolume());
    }
}
