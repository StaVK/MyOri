package ru.myori.service;

import ru.myori.model.StorageProduct;

import java.util.List;

public interface StorageProductService {
    List<StorageProduct> getAll(int storageId);
//    List<StorageProduct> getAllByArticleAndUser(int article, int userId);

    StorageProduct save(StorageProduct storageProduct);

    StorageProduct getByArticleAndPrice(int article, int storageId, float price);

    void createOrUpdate(int article, int storageId, int volume, float price,int userId);

    StorageProduct getFirstByArticle(int article, int storageId);


}
