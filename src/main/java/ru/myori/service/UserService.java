package ru.myori.service;

import ru.myori.model.User;
import ru.myori.to.UserTo;
import ru.myori.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void evictCache();

    void update(UserTo user);

    List<User> getAll();

    void enable(int id, boolean enable);

}
