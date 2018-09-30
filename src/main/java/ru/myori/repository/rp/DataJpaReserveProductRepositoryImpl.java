package ru.myori.repository.rp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.myori.model.ReserveProduct;

import java.util.List;

@Repository
public class DataJpaReserveProductRepositoryImpl implements ReserveProductRepository {

    @Autowired
    CrudReserveProductRepository crudReserveProductRepository;

    @Override
    public int update(int opId, int reserveVolume) {
        return crudReserveProductRepository.update(opId,reserveVolume);
    }

    @Override
    public List<ReserveProduct> getByOp(int opId) {
        return crudReserveProductRepository.getByOp(opId);
    }

    @Override
    public ReserveProduct getBySp(int spId) {
        return crudReserveProductRepository.getBySp(spId);
    }

    @Override
    public ReserveProduct save(ReserveProduct reserveProduct) {
        Assert.notNull(reserveProduct,"Reserve product must not be null");
        return crudReserveProductRepository.save(reserveProduct);
    }

    @Override
    public List<ReserveProduct> getAllByUserAndArticle(int userId, int article) {
        return crudReserveProductRepository.getAllByUserAndArticle(userId,article);
    }

    @Override
    public boolean delete(int rpId) {
        return crudReserveProductRepository.delete(rpId)!=0;
    }

    @Override
    public ReserveProduct getReserveVolume(int article) {
        return crudReserveProductRepository.getReserveVolume(article);
    }

    @Override
    public Long sumReserveVolumeByStorageProduct_SpId(int spId) {
        return crudReserveProductRepository.sumReserveVolumeByStorageProduct_SpId(spId);
    }

    @Override
    public Long sumInReserve(int article, int userId) {
        return crudReserveProductRepository.sumInReserve(article, userId);
    }

    @Override
    public ReserveProduct getLastByOrderProductOpId(int opId) {
        return crudReserveProductRepository.getLastByOrderProductOpId(opId);
    }

    @Override
    public Long sumReserveVolumeByStorageProduct_OpId(int opId) {
        return crudReserveProductRepository.sumReserveVolumeByStorageProduct_OpId(opId);
    }

    @Override
    public ReserveProduct getBySpAndOp(int spId, int opId) {
        return crudReserveProductRepository.getByStorageProduct_SpIdAndOrderProduct_OpId(spId, opId);
    }
}
