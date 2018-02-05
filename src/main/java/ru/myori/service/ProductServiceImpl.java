package ru.myori.service;

import org.springframework.stereotype.Service;
import ru.myori.model.Product;
import ru.myori.repository.ProductRepository;
import ru.myori.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private  final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

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
        return null;
    }

    @Override
    public Product update(Product product) throws NotFoundException {
        return null;
    }

    @Override
    public Product create(Product product) {
        return null;
    }
}
