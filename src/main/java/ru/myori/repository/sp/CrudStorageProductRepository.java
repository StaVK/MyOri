package ru.myori.repository.sp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.OrderProduct;
import ru.myori.model.StorageProduct;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudStorageProductRepository extends JpaRepository<StorageProduct, Integer> {

    @Query("SELECT sp FROM StorageProduct sp WHERE sp.storage.storageId=:storageId")
    List<StorageProduct> getAllByStorage(@Param("storageId") int storageId);

//    List<StorageProduct> getAllByProductArticleAndStorageUserId(int article, int userId);

    @Query("SELECT sp FROM StorageProduct sp WHERE sp.product.article=:article AND sp.storage.storageId=:storageId AND sp.price=:price ORDER BY article ASC")
    StorageProduct getByArticleAndPrice(
            @Param("article") int article,
            @Param("storageId") int storageId,
            @Param("price") float price);

    @Transactional
    @Modifying
    @Query("UPDATE StorageProduct sp SET sp.volume=:volume WHERE sp.spId=:spId")
    int update(@Param("spId") int spId, @Param("volume") int volume);

    @Transactional
    @Modifying
    @Query("DELETE FROM StorageProduct sp WHERE sp.spId=:spId")
    int delete(@Param("spId") int spId);


    @Query(value = "SELECT sp FROM StorageProduct sp WHERE sp.spId = (SELECT MIN(sp.spId) FROM StorageProduct sp WHERE sp.product.article=:article AND sp.storage.storageId=:storageId)")
    StorageProduct getByArticleAndStorageId(@Param("article") int article, @Param("storageId") int storageId);

    List<StorageProduct> findAllByProduct_articleAndStorage_StorageId(int article, int storageId);

    List<StorageProduct> findAllByProduct_articleAndStorage_UserId(int article, int userId);

    @Query("SELECT SUM(sp.volume) FROM StorageProduct sp WHERE sp.product.article=:article AND sp.storage.user.id=:userId")
    Long getFreeVolume(@Param("article") int article, @Param("userId") int userId);

    @Query("SELECT sp FROM StorageProduct sp LEFT JOIN sp.reserve rp WHERE " +
            "sp.product.article=:article AND sp.storage.user.id=:userId AND " +
            "(sp.volume > (SELECT SUM(rp.reserveVolume) FROM ReserveProduct rp WHERE rp.storageProduct.spId=sp.spId) OR rp.rpId IS NULL)")
    List<StorageProduct> getAllWithFreeVolume(@Param("article") int article, @Param("userId") int userId);

/*    @Query("SELECT SUM(sp.reserve.reserveVolume) FROM StorageProduct sp WHERE sp.spId=:spId")
    int countReserveBySpId(@Param("spId") int spId);*/
}
