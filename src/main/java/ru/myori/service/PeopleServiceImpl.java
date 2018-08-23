package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.People;
import ru.myori.repository.people.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public People get(int id) {
        return peopleRepository.get(id);
    }

    @Override
    public People save(People people) {
        return peopleRepository.save(people);
    }
}
