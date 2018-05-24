package ru.myori.web.bp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Box;
import ru.myori.model.BoxProduct;
import ru.myori.service.BoxProductService;
import ru.myori.service.BoxService;

import java.util.List;

public class AbstractBoxProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BoxProductService boxProductService;

    public List<BoxProduct> getAllByBox(int boxId) {
        int userId = AuthorizedUser.id();
        log.info("get all box {} products for User {}", boxId, userId);
        return boxProductService.getAllByBox(boxId);
    }

}
