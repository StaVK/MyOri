package ru.myori.service;

import ru.myori.model.BoxProduct;

import java.util.List;

public interface BoxProductService {
    List<BoxProduct> getAllByBox(int boxId);
}
