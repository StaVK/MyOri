package ru.myori.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;

@Transactional(readOnly = true)
public interface CrudOrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Override
    @Transactional
    OrderProduct save(OrderProduct orderProduct);

    @Transactional
    @Modifying
    @Query("UPDATE OrderProduct op SET op.volume=:volume WHERE op.order=:order AND op.product=:prod")
    int update(@Param("order") Order order, @Param("prod") Product prod, @Param("volume") int volume);

    @Query("SELECT op FROM OrderProduct op JOIN FETCH op.order WHERE op.order.id=:orderId AND op.product.id=:prodId")
    OrderProduct getProd(@Param("orderId") int orderId, @Param("prodId") int prodId);

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderProduct op WHERE op.id=:id")
    int delete(@Param("id") int id);
}
