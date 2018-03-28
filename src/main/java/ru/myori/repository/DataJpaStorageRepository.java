package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.Product;
import ru.myori.model.Storage;

import java.util.HashSet;
import java.util.Set;

@Repository
public class DataJpaStorageRepository implements StorageRepository {

    @Autowired
    CrudStorageRepository crudStorageRepository;

    @Override
    public Storage get(int storageId){
        return crudStorageRepository.findOne(storageId);
    }

    @Override
    public Set<Storage> getAll(int userId){
        return new HashSet<Storage>(crudStorageRepository.getAll(userId));
    }

    @Override
    public Set<Product> getProducts(int storageId, int userId){
        return new HashSet<Product>(crudStorageRepository.getProducts());
//        return null;
    }
}
