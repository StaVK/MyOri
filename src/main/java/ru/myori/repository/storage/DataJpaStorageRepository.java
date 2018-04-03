package ru.myori.repository.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Product;
import ru.myori.model.Storage;
import ru.myori.repository.storage.CrudStorageRepository;
import ru.myori.repository.storage.StorageRepository;
import ru.myori.repository.user.CrudUserRepository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class DataJpaStorageRepository implements StorageRepository {

    @Autowired
    CrudStorageRepository crudStorageRepository;

    @Autowired
    CrudUserRepository crudUserRepository;

    @Override
    public Storage get(int storageId, int userId) {
        Storage storage = crudStorageRepository.findOne(storageId);
        return storage != null && storage.getUser().getId() == userId ? storage : null;
    }

    @Override
    public Set<Storage> getAll(int userId) {
        return new HashSet<Storage>(crudStorageRepository.getAll(userId));
    }

    @Override
    public Set<Product> getProducts(int storageId, int userId) {
        return new HashSet<Product>(crudStorageRepository.getProducts());
//        return null;
    }

    @Override
    @Transactional
    public Storage save(Storage storage, int userId) {
        if (!storage.isNew() && get(storage.getStorageId(), userId) == null) {
            return null;
        }
        storage.setUser(crudUserRepository.findOne(userId));
        return crudStorageRepository.save(storage);
    }

    public int update(int storageId, Storage storage, int userId){
        return crudStorageRepository.update(storageId,storage.getName(),userId);
    }
}
