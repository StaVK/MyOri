package ru.myori.repository.bp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.BoxProduct;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudBoxProductRepository extends JpaRepository<BoxProduct, Integer> {



//    List<BoxProduct> getAll();

    /*@Query("SELECT bp FROM BoxProduct bp WHERE bp.box.boxId=:boxId ORDER BY bp.product.article")
    List<BoxProduct> getAll(@Param("boxId") int boxId);*/

}
