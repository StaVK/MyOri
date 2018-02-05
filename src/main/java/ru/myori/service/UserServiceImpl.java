package ru.myori.service;

import org.springframework.stereotype.Service;
import ru.myori.model.User;
import ru.myori.repository.UserRepository;
import ru.myori.to.UserTo;
import ru.myori.util.exception.NotFoundException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public User get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void evictCache() {

    }

    @Override
    public void update(UserTo user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void enable(int id, boolean enable) {

    }

    @Override
    public User getWithMeals(int id) {
        return null;
    }
}
