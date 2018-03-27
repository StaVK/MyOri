package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.Storage;
import ru.myori.repository.StorageRepository;

import java.util.Set;

@Service
public class StorageServiceImpl implements StorageService {

    private  final StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public Storage get(int storageId) {
        return storageRepository.get(storageId);
    }

    @Override
    public Set<Storage> getAll(int userId){
        return storageRepository.getAll(userId);
    }
}
