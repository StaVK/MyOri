package ru.myori.repository.rp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.ReserveProduct;

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
}
