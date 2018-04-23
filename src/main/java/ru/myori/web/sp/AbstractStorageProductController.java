package ru.myori.web.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Product;
import ru.myori.model.Storage;
import ru.myori.model.StorageProduct;
import ru.myori.service.ProductService;
import ru.myori.service.StorageProductService;
import ru.myori.service.StorageService;

import java.util.List;
import java.util.Set;

public abstract class AbstractStorageProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageProductService storageProductService;

    public List<StorageProduct> getProducts(int storageId){
        int userId = AuthorizedUser.id();
        log.info("getProducts for User {}", userId);
        return storageProductService.getAll(storageId);
    }

    public StorageProduct create(StorageProduct storageProduct) {
        int userId = AuthorizedUser.id();
//        checkNew(meal);
        log.info("create {} for User {}", storageProduct, userId);
        return storageProductService.save(storageProduct);
    }

    public void update(StorageProduct storageProduct) {
        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} for User {}", storageProduct, userId);
//        storageProductService.update(storageId, storage, userId);
    }

    public StorageProduct getByArticle(int article, int storageId){
        int userId = AuthorizedUser.id();
        log.info("getByArticle {} and by storage {} for User {}", article, storageId, userId);
        return storageProductService.getByArticle(article, storageId);
    }

    public void createOrUpdate(int article, int storageId, int volume, float price){
        int userId = AuthorizedUser.id();
        storageProductService.createOrUpdate(article, storageId, volume, price, userId);
    }


/*    public Storage get(int storageId){
        int userId = AuthorizedUser.id();
        log.info("get storage {} for User {}", storageId, userId);
        return storageService.get(storageId, userId);
    }

    public Set<Storage> getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll storages for User {}", userId);
        return storageService.getAll(userId);
    }



*/
}
