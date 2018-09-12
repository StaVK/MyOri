package ru.myori.repository.sp;

import ru.myori.model.StorageProduct;

import java.util.List;
import java.util.Set;

public interface StorageProductRepository {

    List<StorageProduct> getAll(int storageId);

    Set<StorageProduct> getAllByArticle(int article, int storageId);

    StorageProduct save(StorageProduct storageProduct);

    StorageProduct getByArticleAndPrice(int article, int storageId, float price);

    StorageProduct getFirstByArticle(int article, int storageId);

    //TODO Исправить название метода или переделать метод, сейчас просто считает объем (без вычета резерва)
    Long getFreeVolume(int article, int userId);

    int update(StorageProduct storageProduct);

    boolean delete(int spId);

    List<StorageProduct> getAllByArticleAndUser(int article, int userId);

    int sumInReserve(int article, int userId);

    List<StorageProduct> getAllWithFreeVolume(int article, int userId);

}
