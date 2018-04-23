package ru.myori.repository.sp;

import ru.myori.model.StorageProduct;

import java.util.List;

public interface StorageProductRepository {

    List<StorageProduct> getAll(int storageId);

    StorageProduct save(StorageProduct storageProduct);

    StorageProduct getByArticle(int article, int storageId);

    int update(StorageProduct storageProduct);
}
