package ru.myori.repository.rp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.ReserveProduct;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudReserveProductRepository extends JpaRepository<ReserveProduct, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE ReserveProduct rp SET rp.reserveVolume=:reserveVolume WHERE rp.orderProduct.opId=:opId")
    int update(
            @Param("opId") int opId,
            @Param("reserveVolume") int reserveVolume
    );

    @Query("SELECT rp FROM ReserveProduct rp WHERE rp.orderProduct.opId=:opId")
    List<ReserveProduct> getByOp(@Param("opId") int opId);

    @Query("SELECT rp FROM ReserveProduct rp WHERE rp.storageProduct.spId=:spId")
    ReserveProduct getBySp(@Param("spId") int spId);

    @Query("SELECT rp FROM ReserveProduct rp WHERE rp.user.id=:userId AND rp.storageProduct.product.article=:article")
    List<ReserveProduct> getAllByUserAndArticle(@Param("userId") int userId, @Param("article") int article);

    @Query("SELECT new ReserveProduct(rp.storageProduct, rp.orderProduct, SUM(rp.reserveVolume),rp.user) FROM ReserveProduct rp WHERE rp.storageProduct.product.article=:article GROUP BY rp.storageProduct.product.article")
    ReserveProduct getReserveVolume(@Param("article") int article);

    @Transactional
    @Modifying
    @Query("DELETE FROM ReserveProduct rp WHERE rp.rpId=:rpId")
    int delete(@Param("rpId") int rpId);

//    int amountReserveForThisSp();

    @Query("SELECT SUM(rp.reserveVolume) FROM ReserveProduct rp WHERE rp.storageProduct.spId=:spId")
    Long sumReserveVolumeByStorageProduct_SpId(@Param("spId") int spId);

    @Query("SELECT SUM(rp.reserveVolume) FROM ReserveProduct rp WHERE rp.orderProduct.opId=:opId")
    Long sumReserveVolumeByStorageProduct_OpId(@Param("opId") int opId);


    @Query("SELECT SUM(rp.reserveVolume) FROM ReserveProduct rp WHERE rp.storageProduct.product.article=:article AND rp.storageProduct.storage.user.id=:userId")
    Long sumInReserve(@Param("article") int article, @Param("userId") int userId);

    ReserveProduct getLastByOrderProductOpId(int opId);
}
