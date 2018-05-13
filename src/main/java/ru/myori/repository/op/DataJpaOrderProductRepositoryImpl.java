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
    public boolean delete(int prodId) {
        return crudOrderProductRepository.delete(prodId)!=0;
    }

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        if(orderProduct.getOpId()!=null && get(orderProduct).getOpId()!=null){
            update(orderProduct);
            return get(orderProduct);
        }
        return crudOrderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderProduct> getAll(int orderId) {
        return crudOrderProductRepository.getAllByOrderId(orderId);
    }

    @Override
    public List<OrderProduct> getAllForSummary(int userId) {
        return crudOrderProductRepository.getAll(userId);
    }

    @Override
    public OrderProduct get(OrderProduct orderProduct) {
        return crudOrderProductRepository.findOne(orderProduct.getOpId());
    }

    @Override
    public OrderProduct getProd(int orderId, int prodId) {
        return crudOrderProductRepository.getProd(orderId, prodId);
    }

    @Override
    public int update(int orderId, int article, int volume) {
        return crudOrderProductRepository.update(
                crudOrderRepository.findOne(orderId),
                crudProductRepository.getProductByArticle(article),
                volume);
    }
    public int update(OrderProduct orderProduct){
        return crudOrderProductRepository.update(orderProduct.getOrder(),orderProduct.getProduct(),orderProduct.getVolume());
    }
}
