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
//        StorageProduct storageProduct;
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
/*            for (Storage storage : storages) {

//                storageProduct = storageProductRepository.getFirstByArticle(article, storage.getStorageId());

                Set<StorageProduct> storageProductSet = storageProductRepository.getAllByArticle(article, storage.getStorageId());

                for (StorageProduct storageProduct : storageProductSet) {
                    Long tmp = reserveProductRepository.sumReserveVolumeByStorageProduct_SpId(storageProduct.getSpId());
                    if (tmp != null) {
                        totalReservedVolumeForThisArticle += tmp.intValue();
                        available += storageProduct.getVolume() - totalReservedVolumeForThisArticle;
                    }


                }

                //================
                *//*for(StorageProduct storageProduct: storageProductSet) {
                    reserveProduct = reserveProductRepository.getByOp(orderProduct.getOpId());
                    if (reserveProduct != null) {
                        totalReservedVolumeForThisArticle += reserveProduct.getReserveVolume();
                    }
                    available += storageProduct.getVolume() - totalReservedVolumeForThisArticle;
                }*//*
                //====================

            }*/

            orderProductTo = new OrderProductTo(
                    orderProduct,
                    getSummReserveProductReservedVolume(orderProduct.getOpId()),
                    getAvailableStorageProductVolume(article, userId));
            orderProductToList.add(orderProductTo);

        }
        return orderProductToList;
    }
    private int getSummReserveProductReservedVolume(int opId){
        int result=0;
        Long tmp=reserveProductRepository.sumReserveVolumeByStorageProduct_OpId(opId);
        if(tmp!=null){
            result=tmp.intValue();
        }

        return result;
    }

    private int getAvailableStorageProductVolume(int article, int userId) {
        int result=0;
        List<StorageProduct> storageProductList = storageProductRepository.getAllByArticleAndUser(article, userId);
        for (StorageProduct storageProduct : storageProductList) {
            int spVolume=storageProduct.getVolume();
            int rpVolume=0;
            Long tmp=reserveProductRepository.sumReserveVolumeByStorageProduct_SpId(storageProduct.getSpId());
            if(tmp!=null){
                rpVolume=tmp.intValue();
            }
            result+=spVolume-rpVolume;
        }
        return result;
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


}
