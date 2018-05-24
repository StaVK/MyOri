package ru.myori.repository.sp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.OrderProduct;
import ru.myori.model.StorageProduct;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudStorageProductRepository extends JpaRepository<StorageProduct, Integer> {

    @Query("SELECT sp FROM StorageProduct sp WHERE sp.storage.storageId=:storageId")
    List<StorageProduct> getAllByStorage(@Param("storageId") int storageId);

    @Query("SELECT sp FROM StorageProduct sp WHERE sp.product.article=:article AND sp.storage.storageId=:storageId")
    StorageProduct getByArticleAndStorage(@Param("article") int article, @Param("storageId") int storageId);

    @Transactional
    @Modifying
    @Query("UPDATE StorageProduct sp SET sp.volume=:volume WHERE sp.spId=:spId")
    int update(@Param("spId") int spId, @Param("volume") int volume);

    @Transactional
    @Modifying
    @Query("DELETE FROM StorageProduct sp WHERE sp.spId=:spId")
    int delete(@Param("spId") int spId);
}
