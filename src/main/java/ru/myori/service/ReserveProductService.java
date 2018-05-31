package ru.myori.service;

import ru.myori.model.ReserveProduct;

public interface ReserveProductService {
    int update(int userId, int opId, int reserveVolume);
//    ReserveProduct getByOp(int opId);
}
