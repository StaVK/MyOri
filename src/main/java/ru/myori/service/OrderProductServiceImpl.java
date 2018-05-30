package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.OrderProduct;
import ru.myori.model.ReserveProduct;
import ru.myori.model.StorageProduct;
import ru.myori.repository.op.OrderProductRepository;
import ru.myori.repository.sp.StorageProductRepository;
import ru.myori.to.OrderProductTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    private final StorageProductRepository storageProductRepository;

    @Autowired
    public OrderProductServiceImpl(OrderProductRepository orderProductRepository, StorageProductRepository storageProductRepository) {
        this.orderProductRepository = orderProductRepository;
        this.storageProductRepository = storageProductRepository;
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
    public List<OrderProductTo> getAll(int orderId) {
        List<OrderProduct> orderProductList=orderProductRepository.getAll(orderId);
        List<OrderProductTo> orderProductToList=new ArrayList<>();
        OrderProductTo orderProductTo=null;
        OrderProduct orderProduct=null;
        StorageProduct storageProduct;
        Set<ReserveProduct> reserve;

        for (int i = 0; i < orderProductList.size(); i++) {
            orderProduct=orderProductList.get(i);
            storageProduct=storageProductRepository.getByArticleAndStorage(orderProduct.getProduct().getArticle(),100014);
            reserve=storageProduct.getReserve();
            orderProductTo=new OrderProductTo(orderProduct, reserve);
            orderProductToList.add(orderProductTo);
        }
        return orderProductToList;
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
