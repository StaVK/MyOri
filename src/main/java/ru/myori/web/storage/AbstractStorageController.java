package ru.myori.web.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Storage;
import ru.myori.service.StorageService;

import java.util.Set;

public abstract class AbstractStorageController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;

    public Storage get(int storageId){
        int userId = AuthorizedUser.id();
        log.info("get storage {} for User {}", storageId, userId);
        return storageService.get(storageId);
    }

    public Set<Storage> getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll storages for User {}", userId);
        return storageService.getAll(userId);
    }
}
