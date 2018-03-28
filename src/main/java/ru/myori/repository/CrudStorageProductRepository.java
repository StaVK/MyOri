package ru.myori.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
