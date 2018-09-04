package ru.myori.repository.boxProduct;

import ru.myori.model.BoxProduct;

import java.util.List;

public interface BoxProductRepository {
    BoxProduct save(BoxProduct boxProduct);
    List<BoxProduct> getAllByBox(int boxId);
     boolean delete(int bpId);
}
