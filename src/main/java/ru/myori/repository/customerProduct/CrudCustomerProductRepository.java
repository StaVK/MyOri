package ru.myori.repository.customerProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.CustomerProduct;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudCustomerProductRepository extends JpaRepository<CustomerProduct, Integer> {
    @Transactional
    @Query("SELECT cp FROM CustomerProduct cp WHERE cp.customer.customerId=:customerId")
    List<CustomerProduct> findAll(@Param("customerId") int customerId);
}
