package ru.myori.repository.people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.People;

@Transactional(readOnly = true)
public interface CrudPeopleRepository extends JpaRepository<People,Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM People p WHERE p.peopleId=:peopleId")
    int delete(@Param("peopleId") int peopleId);

    @Override
    @Transactional
    People save(People people);
}
