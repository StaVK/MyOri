package ru.myori.repository.boxProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.BoxProduct;

@Transactional(readOnly = true)
public interface CrudBoxProductRepository extends JpaRepository<BoxProduct, Integer> {



//    List<BoxProduct> getAll();

    /*@Query("SELECT boxProduct FROM BoxProduct boxProduct WHERE boxProduct.box.boxId=:boxId ORDER BY boxProduct.product.article")
    List<BoxProduct> getAll(@Param("boxId") int boxId);*/

    @Transactional
    @Modifying
    @Query("DELETE FROM BoxProduct bp WHERE bp.bpId=:bpId")
    int delete(@Param("bpId") int bpId);

}
