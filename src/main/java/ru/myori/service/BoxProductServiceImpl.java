package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.BoxProduct;
import ru.myori.repository.boxProduct.BoxProductRepository;

import java.util.List;

@Service
public class BoxProductServiceImpl implements BoxProductService {

    @Autowired
    BoxProductRepository boxProductRepository;

    @Override
    public List<BoxProduct> getAllByBox(int boxId) {
        return boxProductRepository.getAllByBox(boxId);
    }
}
