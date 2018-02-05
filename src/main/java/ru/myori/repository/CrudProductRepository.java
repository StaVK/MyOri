package ru.myori.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudProductRepository extends JpaRepository<Product, Integer>{

    @Override
    Product save(Product product);

    @Override
    Product findOne(Integer id);

    @Query("SELECT p FROM Product p ORDER BY p.article DESC")
    List<Product> getAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM Product m WHERE m.id=:id")
    boolean delete(@Param("id") int id);
}
