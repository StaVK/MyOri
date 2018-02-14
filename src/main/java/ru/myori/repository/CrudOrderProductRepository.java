package ru.myori.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.OrderProduct;

@Transactional(readOnly = true)
public interface CrudOrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Override
    @Transactional
    OrderProduct save(OrderProduct orderProduct);
}
