package ru.myori.repository;

import ru.myori.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {
    Product save(Product product);

    Product get(int id);

    List<Product> getAll();

    boolean delete(int id);

}
