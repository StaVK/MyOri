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
        User user=userRepository.get(userId);
        Set<Storage> storageSet=user.getStorages();

        OrderProduct orderProduct=orderProductRepository.get(opId);
        int article=orderProduct.getProduct().getArticle();

        StorageProduct storageProduct;
        int result=0;
        for (Storage storage : storageSet) {
            storageProduct = storageProductRepository.getByArticleAndStorage(article, storage.getStorageId());
            if(storageProduct!=null){
                ReserveProduct reserveProduct=reserveProductRepository.getByOp(opId);
                if(reserveProduct==null){
                    reserveProduct=reserveProductRepository.save(new ReserveProduct(storageProduct,orderProduct,0, user));
                }
                List<ReserveProduct> reserveProductList=reserveProductRepository.getAllByUserAndArticle(userId,article);
                int totalReservedVolumeForThisArticle=0;
                for(ReserveProduct rp : reserveProductList){
                    totalReservedVolumeForThisArticle+=rp.getReserveVolume();
                }
                int shift=reserveVolume-reserveProduct.getReserveVolume();

                if(storageProduct.getVolume()>=totalReservedVolumeForThisArticle+shift) {
                    reserveProduct.setReserveVolume(reserveVolume);
                    result=reserveProductRepository.update(opId, reserveVolume);
                }
                break;
            }
        }

        return result;
    }
}
