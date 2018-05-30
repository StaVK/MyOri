package ru.myori.web.rp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.service.ReserveProductService;

public class AbstractReserveProductController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ReserveProductService reserveProductService;

    public void update(int opId, int reservedVolume) {
        log.info("update ReserveProduct {} set volume {}", opId, reservedVolume);
        reserveProductService.update(opId, reservedVolume);
    }
}
