package ru.myori.service;

import ru.myori.model.Product;
import ru.myori.model.Storage;

import java.util.Set;

public interface StorageService {
    Storage get(int storageId);
    Set<Storage> getAll(int userId);
    Set<Product> getProducts(int storageId, int userId);
}
