package ru.myori.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Customer;
import ru.myori.model.People;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudCustomerRepository extends JpaRepository<Customer, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Customer cust WHERE cust.customerId=:customerId")
    int delete(@Param("customerId") int customerId);

    List<Customer> findAllByUserId(int userId);

    @Transactional
    Customer save(Customer customer);

}
