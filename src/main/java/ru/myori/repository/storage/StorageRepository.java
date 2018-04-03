package ru.myori.repository.storage;

import ru.myori.model.Product;
import ru.myori.model.Storage;

import java.util.Set;

public interface StorageRepository {
    Storage get(int storageId, int userId);

    Set<Storage> getAll(int userId);

    Set<Product> getProducts(int storageId, int userId);

    Storage save(Storage storage,int userId);

    int update(int storageId, Storage storage, int userId);
}
