package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.*;
import ru.myori.repository.op.OrderProductRepository;
import ru.myori.repository.rp.ReserveProductRepository;
import ru.myori.repository.sp.StorageProductRepository;
import ru.myori.repository.user.UserRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class ReserveProductServiceImpl implements ReserveProductService {

    @Autowired
    ReserveProductRepository reserveProductRepository;

    @Autowired
    StorageProductRepository storageProductRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public int update(int userId, int opId, int reserveVolume) {
        User user = userRepository.get(userId);
        Set<Storage> storages = user.getStorages();
        OrderProduct orderProduct = orderProductRepository.get(opId);
        int article = orderProduct.getProduct().getArticle();


        ReserveProduct reserveProduct = null;

        List<StorageProduct> storageProductList = storageProductRepository.getAllByArticleAndUser(article, userId);

        Long tmp = reserveProductRepository.sumInReserve(article, userId);
        int sumInReserve=0;

//        Integer sumInReserve = tmp != null ? tmp.intValue() : null;

        if(tmp!=null){
            sumInReserve= tmp.intValue();
        }

        if (reserveVolume > sumInReserve) {
            int totalForReserve = reserveVolume-sumInReserve;
//            while (totalForReserve > 0) {
                for (StorageProduct storageProduct : storageProductList) {
                    int quantityReservedForThisSp=0;
                    Long tmp1=reserveProductRepository.sumReserveVolumeByStorageProduct_SpId(storageProduct.getSpId());
                    if(tmp1!=null){
                        quantityReservedForThisSp= tmp1.intValue();
                    }

                    int storageProductVolume = storageProduct.getVolume();

                    reserveProduct=reserveProductRepository.getBySp(storageProduct.getSpId());

                    if (reserveProduct == null) {
                        reserveProduct=new ReserveProduct(storageProduct,orderProduct,0,user);

                    }
                    if (quantityReservedForThisSp <= storageProductVolume) {
                        int maxForReserve = Math.max(totalForReserve, storageProductVolume);
                        reserveProduct.setReserveVolume(reserveProduct.getReserveVolume()+maxForReserve);
                        totalForReserve -= maxForReserve;
                        reserveProductRepository.save(reserveProduct);
                    }


                    if(totalForReserve==0) break;
                }


//            }
        } else {

            int totalForUnReserve=sumInReserve-reserveVolume;

            while(totalForUnReserve>0) {
                reserveProduct = reserveProductRepository.getLastByOrderProductOpId(orderProduct.getOpId());
                int reserveVolumeInThisRp = reserveProduct.getReserveVolume();

                int minForUnReserve = Math.min(reserveVolumeInThisRp, totalForUnReserve);

                reserveProduct.setReserveVolume(reserveProduct.getReserveVolume()-minForUnReserve);
                reserveProductRepository.save(reserveProduct);
                totalForUnReserve -= minForUnReserve;
            }
        }



/*        while (totalForReserve>0) {

            StorageProduct storageProduct = storageProductRepository.getFirstNotReserved(article, userId);
            if(storageProduct!=null) {
                int storageProductVolume = storageProduct.getVolume();
                int quantityReservedForThisSp = reserveProductRepository.sumReserveVolumeByStorageProduct_SpId(storageProduct.getSpId());
                reserveProduct = reserveProductRepository.getBySp(storageProduct.getSpId());
                if(reserveProduct==null) {
                    reserveProduct = new ReserveProduct();
                    reserveProduct.setStorageProduct(storageProduct);
                    reserveProduct.setOrderProduct(orderProduct);
                    reserveProduct.setUser(user);
                }

                if (totalForReserve > quantityReservedForThisSp) {
                    if (storageProductVolume >= totalForReserve) {
                        reserveProduct.setReserveVolume(totalForReserve);
                    } else {
                        totalForReserve -= storageProductVolume;
                        reserveProduct.setReserveVolume(storageProductVolume);

                    }
                } else {
                    reserveProduct.setReserveVolume(totalForReserve);
                    totalForReserve = 0;
                }

                reserveProductRepository.save(reserveProduct);
            }
        }*/


//============================================


//        if(freeQuantityToReserve(article, user)>0){

//        }

/*        User user = userRepository.get(userId);
        Set<Storage> storageSet = user.getStorages();

        OrderProduct orderProduct = orderProductRepository.get(opId);
        int article = orderProduct.getProduct().getArticle();

//        StorageProduct storageProduct;
        int result = 0;
        for (Storage storage : storageSet) {
//            storageProduct = storageProductRepository.getFirstNotReserved(article, storage.getStorageId());
            Set<StorageProduct> storageProductSet = storageProductRepository.getAllByArticle(article, storage.getStorageId());
            int totalReservedVolumeForThisArticle = 0;
            for (StorageProduct storageProduct : storageProductSet) {

//            if(storageProduct!=null){
                ReserveProduct reserveProduct = reserveProductRepository.getBySp(storageProduct.getSpId());

                if (reserveProduct != null) {
                    reserveProduct.setReserveVolume(reserveProduct.getReserveVolume());
                    reserveProductRepository.save(reserveProduct);
                } else {
                    reserveProduct = reserveProductRepository.save(new ReserveProduct(storageProduct, orderProduct, reserveVolume, user));
                }


                List<ReserveProduct> reserveProductList = reserveProductRepository.getAllByUserAndArticle(userId, article);

                for (ReserveProduct rp : reserveProductList) {
                    totalReservedVolumeForThisArticle += rp.getReserveVolume();
                }
                int shift = reserveVolume - reserveProduct.getReserveVolume();

                if (storageProduct.getVolume() >= totalReservedVolumeForThisArticle + shift) {
                    reserveProduct.setReserveVolume(reserveVolume);
                    result = reserveProductRepository.update(opId, reserveVolume);
                }
                break;
//            }
            }
        }*/

        return 0;
    }

    private int freeQuantityToReserve(int article, User user) {
        int freeQuantityToReserve = 0;
//        User user = userRepository.get(userId);
        Set<Storage> storageSet = user.getStorages();

        for (Storage storage : storageSet) {
            Set<StorageProduct> storageProductSet = storageProductRepository.getAllByArticle(article, storage.getStorageId());
            for (StorageProduct storageProduct : storageProductSet) {
                int volumeAtThisPrice = storageProduct.getVolume();
                int volumeInReserve = 0;
                Set<ReserveProduct> reserve = storageProduct.getReserve();
                for (ReserveProduct reserveProduct : reserve) {
                    volumeInReserve += reserveProduct.getReserveVolume();
                }
                freeQuantityToReserve += volumeAtThisPrice - volumeInReserve;
            }
        }
        return freeQuantityToReserve;
    }
}
