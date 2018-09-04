package ru.myori.repository.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.People;

@Repository
public class DataJpaPeopleRepository implements PeopleRepository {

    @Autowired
    private CrudPeopleRepository crudPeopleRepository;

    @Override
    public People get(int id){
        return crudPeopleRepository.findOne(id);
    }

    @Override
    public People save(People people) {
        return crudPeopleRepository.save(people);
    }

    @Override
    public boolean delete(int peopleId) {
        return crudPeopleRepository.delete(peopleId)!=0;
    }
}
