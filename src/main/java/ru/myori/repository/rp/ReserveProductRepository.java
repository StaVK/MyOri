package ru.myori.repository.rp;

import ru.myori.model.ReserveProduct;

import java.util.List;

public interface ReserveProductRepository {
    int update(int opId, int reserveVolume);
    ReserveProduct getByOp(int opId);

    ReserveProduct save(ReserveProduct reserveProduct);

    List<ReserveProduct> getAllByUserAndArticle(int userId, int article);
}
