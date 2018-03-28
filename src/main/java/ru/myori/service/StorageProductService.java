package ru.myori.service;

import ru.myori.model.StorageProduct;

import java.util.List;

public interface StorageProductService {
    List<StorageProduct> getAll(int storageId);
}
