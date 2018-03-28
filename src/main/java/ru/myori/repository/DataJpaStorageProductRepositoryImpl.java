package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.StorageProduct;

import java.util.List;

@Repository
public class DataJpaStorageProductRepositoryImpl implements StorageProductRepository {

    @Autowired
    CrudStorageProductRepository crudStorageProductRepository;

    @Override
    public List<StorageProduct> getAll(int storageId) {
        return crudStorageProductRepository.getAllByStorage(storageId);
    }
}
