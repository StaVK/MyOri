package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.myori.model.Product;
import ru.myori.repository.ProductRepository;
import ru.myori.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product get(int id) throws NotFoundException {
        return productRepository.get(id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        productRepository.delete(id);
    }

    @Override
    public List<Product> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<Product> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product update(Product product) throws NotFoundException {
        return productRepository.save(product);
    }

    @Override
    public Product create(Product product) {
        Assert.notNull(product, "product must not be null");
        return productRepository.save(product);
    }
}
