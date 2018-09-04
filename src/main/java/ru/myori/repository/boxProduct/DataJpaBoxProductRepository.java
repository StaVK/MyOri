package ru.myori.repository.boxProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.BoxProduct;

import java.util.List;

@Repository
public class DataJpaBoxProductRepository implements BoxProductRepository {

    @Autowired
    CrudBoxProductRepository crudBoxProductRepository;

    @Override
    public BoxProduct save(BoxProduct boxProduct) {
        return crudBoxProductRepository.save(boxProduct);
    }

    @Override
    public List<BoxProduct> getAllByBox(int boxId) {
        return crudBoxProductRepository.findAll();
    }

    @Override
    public boolean delete(int bpId) {
        return crudBoxProductRepository.delete(bpId)!=0;
    }
}
