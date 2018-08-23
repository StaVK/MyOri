package ru.myori.repository.people;

import ru.myori.model.People;

public interface PeopleRepository {
    // null if not found
    People get(int id);

    People save(People people);
}
