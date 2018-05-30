package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.ReserveProduct;
import ru.myori.repository.rp.ReserveProductRepository;

@Service
public class ReserveProductServiceImpl implements ReserveProductService {

    @Autowired
    ReserveProductRepository reserveProductRepository;

    @Override
    public int update(int opId, int reserveVolume) {
/*        ReserveProduct reserveProduct=reserveProductRepository.getByOp(opId);
        reserveProduct.setReserveVolume(reserveVolume);*/
        return reserveProductRepository.update(opId, reserveVolume);
    }
}
