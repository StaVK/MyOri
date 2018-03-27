package ru.myori.repository;

import ru.myori.model.Storage;

import java.util.Set;

public interface StorageRepository {
    Storage get(int storageId);

    Set<Storage> getAll(int userId);
}
