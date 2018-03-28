package ru.myori.repository;

import ru.myori.model.StorageProduct;

import java.util.List;

public interface StorageProductRepository {

    List<StorageProduct> getAll(int storageId);
}
