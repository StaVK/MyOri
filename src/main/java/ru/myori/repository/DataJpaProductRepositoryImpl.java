package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaProductRepositoryImpl implements ProductRepository{

    @Autowired
    private CrudProductRepository crudProductRepository;

    @Override
    public Product save(Product product) {
        return crudProductRepository.save(product);
    }

    @Override
    public Product get(int id) {
        return crudProductRepository.findOne(id);
    }

    @Override
    public List<Product> getAll() {
        return crudProductRepository.getAll();
    }

    @Override
    public boolean delete(int id) {
        return crudProductRepository.delete(id);
    }
}
