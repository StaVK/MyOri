package ru.myori.repository;

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
    @Query("SELECT o FROM Order o")
    List<Order> getAll();

    @Override
    Order save(Order order);

    @Override
    Order findOne(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id=:id")
    int delete(@Param("id") int id);

}
