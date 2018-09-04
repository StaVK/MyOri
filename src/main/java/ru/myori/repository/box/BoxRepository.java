package ru.myori.repository.box;

import ru.myori.model.Box;
import ru.myori.model.User;

import java.util.List;

public interface BoxRepository {

    List<Box> getAll(int userId);

    Box save(Box box);

    Box get(int boxId);

    boolean delete(int boxId);

}
