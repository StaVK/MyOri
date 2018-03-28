package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.StorageProduct;
import ru.myori.repository.StorageProductRepository;

import java.util.List;

@Service
public class StorageProductServiceImpl implements StorageProductService {

    @Autowired
    StorageProductRepository storageProductRepository;

    @Override
    public List<StorageProduct> getAll(int storageId) {
        return storageProductRepository.getAll(storageId);
    }
}
