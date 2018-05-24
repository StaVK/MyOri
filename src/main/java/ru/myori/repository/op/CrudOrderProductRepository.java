package ru.myori.repository.op;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudOrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Override
    @Transactional
    OrderProduct save(OrderProduct orderProduct);

    OrderProduct findOne(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE OrderProduct op SET op.volume=:volume, op.executedVolume=:executedVolume, op.order=:order, op.product=:product, op.status=:status WHERE op.opId=:opId AND op.status=0")
    int update(
            @Param("opId") int opId,
            @Param("order") Order order,
            @Param("product") Product prod,
            @Param("volume") int volume,
            @Param("executedVolume") int executedVolume,
            @Param("status") int status
    );

    @Query("SELECT op FROM OrderProduct op JOIN FETCH op.order WHERE op.order.orderId=:orderId AND op.product.id=:prodId")
    OrderProduct getProd(@Param("orderId") int orderId, @Param("prodId") int prodId);

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderProduct op WHERE op.opId=:id AND op.status=0")
    int delete(@Param("id") int id);

    @Transactional
    @Query("SELECT op FROM OrderProduct op WHERE op.order.orderId=:orderId")
    List<OrderProduct> getAllByOrderId(@Param("orderId") int orderId);

    @Transactional
    @Query("SELECT new OrderProduct(op.product, SUM(op.volume-op.executedVolume)) FROM OrderProduct op WHERE op.order.orderId IN (SELECT o FROM Order o WHERE o.user.id=:userId) AND (op.volume > op.executedVolume) GROUP BY op.product")
    List<OrderProduct> getAllForSummary(@Param("userId") int userId);
}
