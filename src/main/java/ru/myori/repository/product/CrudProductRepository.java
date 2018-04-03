package ru.myori.repository.product;

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

    @Transactional
    @Modifying
    @Query("UPDATE Product product SET product.description=:description, product.price=:price WHERE product.article=:article")
    int update(@Param("article") int article, @Param("description") String description, @Param("price") double price);

    Product findOne(Integer id);

    @Query("SELECT p FROM Product p ORDER BY p.article")
    List<Product> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.prodId=:id")
    int delete(@Param("id") int id);

    Product getProductByArticle(Integer article);
}
