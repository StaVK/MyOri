package ru.myori.repository.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.Product;
import ru.myori.model.Storage;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudStorageRepository extends JpaRepository<Storage, Integer> {
    @Query("SELECT s FROM Storage s WHERE s.user.id=:userId ORDER BY storageId DESC")
    List<Storage> getAll(@Param("userId") int userId);

    @Query("SELECT p FROM Product p")
    Set<Product> getProducts();

    @Transactional
    @Modifying
    @Query("UPDATE Storage st SET st.name=:name WHERE st.storageId=:storageId AND st.user.id=:userId")
    int update(@Param("storageId") int storageId, @Param("name") String name, @Param("userId") int userId);
}
