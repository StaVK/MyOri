package ru.myori.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaProductRepositoryImpl implements ProductRepository{

    @Autowired
    private CrudProductRepository crudProductRepository;

    @Override
    @Transactional
    public Product save(Product product) {
        return crudProductRepository.save(product);
    }

    public int update(Product product){
        return crudProductRepository.update(product.getArticle(),product.getDescription(),product.getPrice());
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
        return crudProductRepository.delete(id)!=0;
    }

    @Override
    public Product getByArticle(int article) {
        return crudProductRepository.getProductByArticle(article);
    }
}
