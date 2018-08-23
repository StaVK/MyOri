package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.*;
import ru.myori.repository.op.OrderProductRepository;
import ru.myori.repository.rp.ReserveProductRepository;
import ru.myori.repository.sp.StorageProductRepository;
import ru.myori.repository.user.UserRepository;
import ru.myori.to.OrderProductTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    private final StorageProductRepository storageProductRepository;

    private final ReserveProductRepository reserveProductRepository;

    private final UserRepository userRepository;

    @Autowired
    public OrderProductServiceImpl(OrderProductRepository orderProductRepository, StorageProductRepository storageProductRepository, ReserveProductRepository reserveProductRepository, UserRepository userRepository) {
        this.orderProductRepository = orderProductRepository;
        this.storageProductRepository = storageProductRepository;
        this.reserveProductRepository = reserveProductRepository;
        this.userRepository = userRepository;
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
    public List<OrderProductTo> getAll(int orderId, int userId) {
        List<OrderProduct> orderProductList = orderProductRepository.getAll(orderId);
        List<OrderProductTo> orderProductToList = new ArrayList<>();
        OrderProductTo orderProductTo = null;
        OrderProduct orderProduct = null;
        StorageProduct storageProduct;
//        Set<ReserveProduct> reserve;
        ReserveProduct reserveProduct = null;

        for (int i = 0; i < orderProductList.size(); i++) {
            orderProduct = orderProductList.get(i);
            int article = orderProduct.getProduct().getArticle();
            int available = 0;

            User user = userRepository.get(userId);
            final Set<Storage> storages = user.getStorages();

            // Пробегаем по всем складам чтоб вычислить количество которое зарезервированно
            // и доступно для резервирования для данного артикула
            int totalReservedVolumeForThisArticle = 0;
            for (Storage storage : storages) {

                storageProduct = storageProductRepository.getByArticleAndStorage(article, storage.getStorageId());

                if (storageProduct != null) {
                    reserveProduct = reserveProductRepository.getByOp(orderProduct.getOpId());
                    if (reserveProduct != null) {
                        totalReservedVolumeForThisArticle += reserveProduct.getReserveVolume();
                    }
                    available += storageProduct.getVolume() - totalReservedVolumeForThisArticle;
                }
/*
                if (storageProduct != null) {
                    reserveProduct = reserveProductRepository.getByOp(orderProduct.getOpId());
                    // TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    // Вычисляем сколько продукта с данным артикулом зарезервировано на складе
                    List<ReserveProduct> reserveProductList = reserveProductRepository.getAllByUserAndArticle(userId, storageProduct.getProduct().getArticle());
                    int totalReservedVolumeForThisArticle = 0;
                    for (ReserveProduct rp : reserveProductList) {
                        totalReservedVolumeForThisArticle += rp.getReserveVolume();
                    }

                    // Сколько доступно для резервирования
                    available = storageProduct.getVolume() - totalReservedVolumeForThisArticle;
                }
                */

            }

            orderProductTo = new OrderProductTo(orderProduct, reserveProduct, available);
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
        OrderProduct orderProduct = orderProductRepository.get(opId);
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
