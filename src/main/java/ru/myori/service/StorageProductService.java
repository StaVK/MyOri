package ru.myori.service;

import ru.myori.model.StorageProduct;

import java.util.List;

public interface StorageProductService {
    List<StorageProduct> getAll(int storageId);

    StorageProduct save(StorageProduct storageProduct);

    StorageProduct getByArticle(int article, int storageId);

    void createOrUpdate(int article, int storageId, int volume, float price,int userId);


}
