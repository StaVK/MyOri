package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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
        Assert.notNull(people, "people must not be null");
        return peopleRepository.save(people);
    }

    @Override
    public boolean delete(int peopleId) {
        return peopleRepository.delete(peopleId);
    }

    @Override
    public int update(People people) {
        Assert.notNull(people, "people must not be null");

//        Customer customer = updateFromTo(get(userTo.getId()), userTo);
//        Assert.isNull(getByEmail(user.getEmail()), "User with this email already exists!");
        peopleRepository.save(people);
        return 0;
    }
}
