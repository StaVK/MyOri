package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.myori.model.User;
import ru.myori.repository.UserRepository;
import ru.myori.to.UserTo;
import ru.myori.util.exception.NotFoundException;

import java.util.List;

import static ru.myori.util.UserUtil.prepareToSave;
import static ru.myori.util.UserUtil.updateFromTo;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        Assert.isNull(getByEmail(user.getEmail()),"User with this email already exists!");
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        userRepository.delete(id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return userRepository.get(id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return null;
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        Assert.isNull(getByEmail(user.getEmail()),"User with this email already exists!");
        userRepository.save(user);
    }

    @Override
    public void evictCache() {

    }

    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        Assert.isNull(getByEmail(user.getEmail()),"User with this email already exists!");
        userRepository.save(prepareToSave(user));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public User getWithMeals(int id) {
        return null;
    }
}
