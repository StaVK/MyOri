package ru.myori.repository;

import ru.myori.model.Product;
import ru.myori.model.Storage;

import java.util.Set;

public interface StorageRepository {
    Storage get(int storageId);

    Set<Storage> getAll(int userId);

    Set<Product> getProducts(int storageId, int userId);
}
