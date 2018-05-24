package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.*;
import ru.myori.repository.box.BoxRepository;
import ru.myori.repository.bp.BoxProductRepository;
import ru.myori.repository.op.OrderProductRepository;
import ru.myori.repository.order.OrderRepository;
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

    @Override
    public Box get(int boxId) {
        return boxRepository.get(boxId);
    }

    @Override
    public List<Box> getAll(int userId) {
        return boxRepository.getAll(userId);
    }

    @Override
    public Box create(int userId, int customerId) {
        //TODO Доделать создание коробки
        List<Order> orderList = orderRepository.getAllActive(userId, customerId, 0);
        Order order = null;
        Set<OrderProduct> orderProductSet = null;

        int storageId = 100014; //TODO сделать возможность выбора с каких складов формировать коробку.

        Box box=null;

        if(orderList.size()!=0){
            User user=userRepository.get(userId);
            User customer=userRepository.get(customerId);

            box=new Box(user,customer);
            box=boxRepository.save(box);

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

                    storageProduct = storageProductRepository.getByArticleAndStorage(article, storageId);

                    if(storageProduct!=null) {
                        if (storageProduct.getVolume() < orderVolume) {
                            setVolumeInSpAndOp(box, storageProduct, orderProduct, storageProduct.getVolume());
                        } else {
                            setVolumeInSpAndOp(box, storageProduct, orderProduct, orderVolume);
                        }
                    }
                }
            }
        }
        return box;
    }


    @Transactional
    public BoxProduct setVolumeInSpAndOp(Box box, StorageProduct storageProduct, OrderProduct orderProduct, int volume) {
        BoxProduct boxProduct=new BoxProduct();
        boxProduct.setBox(box);
        boxProduct.setProduct(storageProduct.getProduct());
        boxProduct.setVolume(volume);


        if(storageProduct.getVolume()==volume){
            storageProductRepository.delete(storageProduct.getSpId());
        }
        else {
            storageProduct.setVolume(storageProduct.getVolume()-volume);
        }

        orderProduct.setExecutedVolume(orderProduct.getExecutedVolume()+volume);

        if(orderProduct.getVolume()==orderProduct.getExecutedVolume()){
            orderProduct.setStatus(OrderProduct.ORDER_PRODUCT_INBOX);
        }

        boxProductRepository.save(boxProduct);
        storageProductRepository.update(storageProduct);
        orderProductRepository.update(orderProduct);

        return boxProduct;
    }


}
