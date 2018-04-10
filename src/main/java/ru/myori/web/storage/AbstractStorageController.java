package ru.myori.web.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Storage;
import ru.myori.model.StorageProduct;
import ru.myori.service.StorageProductService;
import ru.myori.service.StorageService;

import java.util.List;
import java.util.Set;

public abstract class AbstractStorageController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;

    @Autowired
    private StorageProductService storageProductService;

    public Storage get(int storageId){
        int userId = AuthorizedUser.id();
        log.info("get storage {} for User {}", storageId, userId);
        return storageService.get(storageId, userId);
    }

    public Set<Storage> getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll storages for User {}", userId);
        return storageService.getAll(userId);
    }

    public List<StorageProduct> getProducts(int storageId){
        int userId = AuthorizedUser.id();
        log.info("getProducts for User {}", userId);
        return storageProductService.getAll(storageId);
    }

    public Storage create(Storage storage) {
        int userId = AuthorizedUser.id();
//        checkNew(meal);
        log.info("create {} for User {}", storage, userId);
        return storageService.create(storage, userId);
    }

    public void update(int storageId, Storage storage) {
        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} for User {}", storage, userId);
        storageService.update(storageId, storage, userId);
    }
}
