package ru.myori.web.rp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.service.ReserveProductService;

public class AbstractReserveProductController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ReserveProductService reserveProductService;

    public void update(int opId, int reservedVolume) {
        int userId = AuthorizedUser.id();
        log.info("User {} update ReserveProduct {} set volume {}", userId, opId, reservedVolume);
        reserveProductService.update(userId, opId, reservedVolume);
    }
}
