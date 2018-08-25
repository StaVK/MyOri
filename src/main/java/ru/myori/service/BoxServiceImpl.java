package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.*;
import ru.myori.repository.box.BoxRepository;
import ru.myori.repository.bp.BoxProductRepository;
import ru.myori.repository.customer.CustomerRepository;
import ru.myori.repository.op.OrderProductRepository;
import ru.myori.repository.order.OrderRepository;
import ru.myori.repository.rp.ReserveProductRepository;
import ru.myori.repository.sp.StorageProductRepository;
import ru.myori.repository.user.UserRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    BoxRepository boxRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    StorageProductRepository storageProductRepository;

    @Autowired
    BoxProductRepository boxProductRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReserveProductRepository reserveProductRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Box get(int boxId) {
        return boxRepository.get(boxId);
    }

    @Override
    public List<Box> getAll(int userId) {
        return boxRepository.getAll(userId);
    }

    @Transactional
    @Override
    public Box create(int userId, int customerId) {
        List<Order> orderList = orderRepository.getAllActive(userId, customerId, 0);
        Order order = null;
        Set<OrderProduct> orderProductSet = null;

        User user = userRepository.get(userId);
        Customer customer = customerRepository.get(customerId);

        Set<Storage> storageSet=user.getStorages();

        Box box = null;

        if (orderList.size() != 0) {

            box = new Box(user, customer);
            box = boxRepository.save(box);

            for (int i = 0; i < orderList.size(); i++) {
                order = orderList.get(i);
                orderProductSet = new HashSet<>(orderProductRepository.getAll(order.getOrderId()));

                OrderProduct orderProduct = null;
                int orderVolume = 0;
                int article = 0;
                StorageProduct storageProduct = null;

                Iterator<OrderProduct> iterator = orderProductSet.iterator();
                while (iterator.hasNext()) {
                    orderProduct = iterator.next();
                    orderVolume = orderProduct.getVolume();
                    article = orderProduct.getProduct().getArticle();

                            /*
        Если по данному OrderProduct на складе есть резерв, то сначала в корзину кладется резерв,
        при наличии на складе незарезервированных продуктов, остающийся объем добирается из незарезервированного объема
          */
                    int storageId;
                    for(Storage storage:storageSet) {
                        storageId=storage.getStorageId();
                        storageProduct = storageProductRepository.getByArticleAndStorage(article, storageId);

                        if (storageProduct != null) {
                            BoxProduct boxProduct = new BoxProduct();
                            boxProduct.setBox(box);
                            boxProduct.setProduct(storageProduct.getProduct());
                            ReserveProduct reserveProduct = reserveProductRepository.getByOp(orderProduct.getOpId());
                            if (reserveProduct != null) {
                                // Объем, который был в резерве
                                int reserveVolume = reserveProduct.getReserveVolume();

                                // Удаляем резерв
                                boolean del = reserveProductRepository.delete(reserveProduct.getRpId());

                                for (ReserveProduct rp : storageProduct.getReserve()) {
                                    if (rp.getOrderProduct().getProduct().getArticle().equals(reserveProduct.getOrderProduct().getProduct().getArticle())) {
                                        storageProduct.getReserve().remove(rp);
                                    }
                                }

                                // Уменьшаем объем на складе
                                storageProduct.setVolume(storageProduct.getVolume() - reserveVolume);

                                // Пишем в заказе что его часть исполнена
                                orderProduct.setExecutedVolume(orderProduct.getExecutedVolume() + reserveVolume);

                                // Кладем в коробку
                                boxProduct.setVolume(reserveVolume);

                                storageProductRepository.update(storageProduct); // Берем со склада
                                boxProductRepository.save(boxProduct); // Кладем в корзинку
                                orderProductRepository.update(orderProduct); // Пишем в заказе что его часть исполнена
                            }
                            // Считаем какой объем зарезервирован на складе
                            int reservedVolume = 0;
                            Set<ReserveProduct> reserveProductSet = storageProduct.getReserve();
                            for (ReserveProduct rp : reserveProductSet) {
                                reservedVolume += rp.getReserveVolume();
                            }

                            // Ели остался свободный объем на складе, то докладываем нужное количество в заказ
                            int freeVolume = storageProduct.getVolume() - reservedVolume;
                            if (freeVolume > 0) {
                                int unfulfilledVolume = orderVolume - orderProduct.getExecutedVolume();
                                if (unfulfilledVolume > freeVolume) {
                                    unfulfilledVolume = freeVolume;
                                }
                                storageProduct.setVolume(storageProduct.getVolume() - unfulfilledVolume);
                                boxProduct.setVolume(boxProduct.getVolume() + unfulfilledVolume);
                                orderProduct.setExecutedVolume(orderProduct.getExecutedVolume() + unfulfilledVolume);
                                storageProductRepository.update(storageProduct);
                                boxProductRepository.save(boxProduct);
                                orderProductRepository.update(orderProduct);
                            }
                        }
                    }

                }
            }
        }
        return box;
    }

}
