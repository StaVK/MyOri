package ru.myori.repository.op;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.OrderProduct;
import ru.myori.repository.order.CrudOrderRepository;
import ru.myori.repository.product.CrudProductRepository;

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
    public boolean delete(int opId) {
        return crudOrderProductRepository.delete(opId)!=0;
    }

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        if(orderProduct.getOpId()!=null && get(orderProduct.getOpId()).getOpId()!=null){
            update(orderProduct);
            return get(orderProduct.getOpId());
        }
        return crudOrderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderProduct> getAll(int orderId) {
        return crudOrderProductRepository.getAllByOrderId(orderId);
    }

    @Override
    public List<OrderProduct> getAllForSummary(int userId) {
        return crudOrderProductRepository.getAllForSummary(userId);
    }

    @Override
    public OrderProduct get(int opId) {
        return crudOrderProductRepository.findOne(opId);
    }

    @Override
    public OrderProduct getProd(int orderId, int prodId) {
        return crudOrderProductRepository.getProd(orderId, prodId);
    }

    @Override
    public int update(OrderProduct orderProduct){
        return crudOrderProductRepository.update(
                orderProduct.getOpId(),
                orderProduct.getOrder(),
                orderProduct.getProduct(),
                orderProduct.getVolume(),
                orderProduct.getExecutedVolume(),
                orderProduct.getStatus());
    }

}
