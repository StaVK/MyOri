package ru.myori.service;

import ru.myori.model.Box;
import ru.myori.model.BoxProduct;
import ru.myori.model.OrderProduct;
import ru.myori.model.StorageProduct;

import java.util.List;

public interface BoxService {
    List<Box> getAll(int userId);

    Box create(int userId, int customerId);

    BoxProduct setVolumeInSpAndOp(Box box, StorageProduct storageProduct,OrderProduct orderProduct, int volume);

    Box get(int boxId);
}

