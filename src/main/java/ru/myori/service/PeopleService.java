package ru.myori.service;

import ru.myori.model.People;

public interface PeopleService {

    People get(int id);

    People save(People people);

    boolean delete(int peopleId);
    
    int update(People people);

}
