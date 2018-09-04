package ru.myori.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Customer;
import ru.myori.model.User;
import ru.myori.service.UserService;
import ru.myori.to.UserTo;

import java.util.List;
import java.util.Set;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("get all users");
        return service.getAll();
    }

    public Set<Customer> getCustomers() {
        int userId = AuthorizedUser.id();
        log.info("get all customers for user {}", userId);
        return service.getCustomers(userId);
    }

    public User get(int id) {
        log.info("get user {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create user {}", user);
//        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
//        assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
//        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + id);
        service.enable(id, enabled);
    }

}
