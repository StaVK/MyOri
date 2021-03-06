package ru.myori.repository.product;

import ru.myori.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {
    Product save(Product product);

    Product get(int id);

    List<Product> getAll();

    boolean delete(int id);

    Product getByArticle(int article);

    int update(Product product);

}
