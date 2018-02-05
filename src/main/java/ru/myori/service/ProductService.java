package ru.myori.service;

import ru.myori.model.Product;
import ru.myori.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ProductService{
    Product get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    default List<Product> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
    }

    List<Product> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Product> getAll();

    Product update(Product product) throws NotFoundException;

    Product create(Product product);

}
