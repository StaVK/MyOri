package ru.myori.repository.user;

import ru.myori.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    void delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
