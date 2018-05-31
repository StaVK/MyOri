package ru.myori.repository.rp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    public ReserveProduct getByOp(int opId) {
        return crudReserveProductRepository.getByOp(opId);
    }

    @Override
    public ReserveProduct save(ReserveProduct reserveProduct) {
        return crudReserveProductRepository.save(reserveProduct);
    }

    @Override
    public List<ReserveProduct> getAllByUserAndArticle(int userId, int article) {
        return crudReserveProductRepository.getAllByUserAndArticle(userId,article);
    }
}
