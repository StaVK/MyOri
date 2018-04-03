package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.Product;
import ru.myori.model.Storage;
import ru.myori.repository.storage.StorageRepository;

import java.util.Set;

@Service
public class StorageServiceImpl implements StorageService {

    private  final StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public Storage get(int storageId, int userId) {
        return storageRepository.get(storageId, userId);
    }

    @Override
    public Set<Storage> getAll(int userId){
        return storageRepository.getAll(userId);
    }

    @Override
    public Set<Product> getProducts(int storageId, int userId){
        return storageRepository.getProducts(storageId,userId);
    }

    @Override
    public int update(int storageId, Storage storage, int userId) {
        return storageRepository.update(storageId, storage, userId);
    }

    @Override
    public Storage create(Storage storage, int userId) {
        return storageRepository.save(storage, userId);
    }
}
