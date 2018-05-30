package ru.myori.repository.rp;

import ru.myori.model.ReserveProduct;

public interface ReserveProductRepository {
    int update(int opId, int reserveVolume);
    ReserveProduct getByOp(int opId);
}
