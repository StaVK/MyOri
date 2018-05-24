package ru.myori.repository.box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Box;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudBoxRepository extends JpaRepository<Box, Integer> {

    @Query("SELECT b FROM Box b WHERE b.user.id=:userId")
    List<Box> getAll(@Param("userId") int userId);
}
