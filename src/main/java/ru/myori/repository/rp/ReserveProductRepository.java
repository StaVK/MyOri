package ru.myori.repository.rp;

import ru.myori.model.ReserveProduct;

import java.util.List;

public interface ReserveProductRepository {
    int update(int opId, int reserveVolume);
    List<ReserveProduct> getByOp(int opId);

    ReserveProduct getBySp(int spId);

    ReserveProduct getBySpAndOp(int spId, int opId);

    ReserveProduct save(ReserveProduct reserveProduct);

    List<ReserveProduct> getAllByUserAndArticle(int userId, int article);

    boolean delete(int rpId);

    ReserveProduct getReserveVolume(int article);

    Long sumReserveVolumeByStorageProduct_SpId(int spId);
    Long sumReserveVolumeByStorageProduct_OpId(int opId);

    // Вычисление количества, зарезервированного для даннного артикула
    Long sumInReserve(int article, int userId);

    ReserveProduct getLastByOrderProductOpId(int opId);
}
