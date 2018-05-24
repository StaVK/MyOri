package ru.myori.web.box;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Box;
import ru.myori.service.BoxService;

import java.util.List;

public class AbstractBoxController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BoxService boxService;

    public List<Box> getAll() {
        int userId = AuthorizedUser.id();
        log.info("get all boxes for User {}", userId);
        return boxService.getAll(userId);
    }

    public Box create(int customerId) {
        int userId = AuthorizedUser.id();
        log.info("user {} create box for customer {}", userId, customerId);
        return boxService.create(userId, customerId);
    }
}
