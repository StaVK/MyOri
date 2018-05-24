package ru.myori.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Order;
import ru.myori.model.Product;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudOrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.user.id=:userId")
    List<Order> getAll(@Param("userId") int userId);

    @Query("SELECT o FROM Order o WHERE o.user.id=:userId AND o.forUser.id=:customerId AND o.status=:status")
    List<Order> getAllActive(@Param("userId") int userId, @Param("customerId") int customerId, @Param("status") int status);

    @Override
    Order save(Order order);

    Order findOne(Integer id);

    Order getOrderByOrderIdAndUserId(Integer id, Integer userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id=:id AND o.user.id=:userId")
    void delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Order o SET o.forUser.id=:customerId WHERE o.orderId=:orderId AND o.user.id=:userId")
    void chgCustomer(@Param("customerId") int customerId, @Param("orderId")int orderId, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status=:status WHERE o.orderId=:orderId")
    void changeStatus(@Param("orderId")int orderId, @Param("status") int status);
}
