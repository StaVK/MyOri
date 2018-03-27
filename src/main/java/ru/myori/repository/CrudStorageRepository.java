package ru.myori.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.myori.model.Storage;

import java.util.List;

public interface CrudStorageRepository extends JpaRepository<Storage, Integer> {
    @Query("SELECT s FROM Storage s WHERE s.user.id=:userId")
    List<Storage> getAll(@Param("userId") int userId);
}
