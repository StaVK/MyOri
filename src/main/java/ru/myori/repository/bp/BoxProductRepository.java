package ru.myori.repository.bp;

import ru.myori.model.BoxProduct;

import java.util.List;

public interface BoxProductRepository {
    BoxProduct save(BoxProduct boxProduct);
    List<BoxProduct> getAllByBox(int boxId);
}
