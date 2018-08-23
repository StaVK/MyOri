package ru.myori.service;

import ru.myori.model.People;

public interface PeopleService {

    People get(int id);

    People save(People people);
}
