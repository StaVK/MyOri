package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.PartTree;
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
    public List<OrderProduct> getAllForSummary(int userId) {
        return orderProductRepository.getAllForSummary(userId);
    }

    @Override
    public OrderProduct get(int opId) {
        return orderProductRepository.get(opId);
    }

    @Override
    public OrderProduct getProd(int orderId, int prodId) {
        return orderProductRepository.getProd(orderId, prodId);
    }

    @Override
    public int update(int opId, int volume) {
        OrderProduct orderProduct=orderProductRepository.get(opId);
        orderProduct.setVolume(volume);
        return orderProductRepository.update(orderProduct);
//        return orderProductRepository.update(orderId, article, volume);
    }



/*    @Override
    public int update(OrderProduct orderProduct) {
        return orderProductRepository.update(orderProduct);
    }

    @Override
    public int updateExecutedVolume(OrderProduct orderProduct) {
        return orderProductRepository.updateExecutedVolume(orderProduct);
    }*/
}
