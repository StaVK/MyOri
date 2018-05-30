package ru.myori.repository.rp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.ReserveProduct;

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
    ReserveProduct getByOp(@Param("opId") int opId);
}
