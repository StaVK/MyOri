package ru.myori.repository.people;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myori.model.People;

public interface CrudPeopleRepository extends JpaRepository<People,Integer> {

}
