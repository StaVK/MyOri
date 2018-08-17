package ru.myori.repository.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.Box;
import ru.myori.model.User;

import java.util.List;

@Repository
public class DataJpaBoxRepository implements BoxRepository {

    @Autowired
    CrudBoxRepository crudBoxRepository;

    @Override
    public List<Box> getAll(int userId) {
        return crudBoxRepository.getAll(userId);
    }

    @Override
    public Box save(Box box) {
        return crudBoxRepository.save(box);
    }

    @Override
    public Box get(int boxId) {
        return crudBoxRepository.findOne(boxId);
    }

}
