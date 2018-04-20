package ru.myori.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.myori.model.Storage;
import ru.myori.model.StorageProduct;
import ru.myori.testData.StorageProductTestData;


import java.util.*;

import static ru.myori.Profiles.DATAJPA;
import static ru.myori.testData.StorageTestData.*;
import static ru.myori.testData.StorageTestData.MATCHER;
import static ru.myori.testData.UserTestData.*;
import static ru.myori.testData.StorageProductTestData.*;

@ActiveProfiles(DATAJPA)
public class StorageServiceTest extends AbstractServiceTest {

    @Autowired
    protected StorageService storageService;

    @Autowired
    protected StorageProductService storageProductService;

    @Test
    public void get() {
        Storage actual = storageService.get(STORAGE.getStorageId(), USER_ID);
        MATCHER.assertEquals(STORAGE, actual);
    }

    @Test
    public void getAll() {
        MATCHER.assertListEquals(Arrays.asList(STORAGE), new ArrayList<>(storageService.getAll(USER_ID)));
    }

    @Test
    public void getProducts() {
        Storage storage=storageService.get(STORAGE_ID, USER_ID);
        List<StorageProduct> storageProductList=new ArrayList<StorageProduct>(storage.getProducts());
        StorageProductTestData.MATCHER.assertListEquals(STORAGE_PRODUCT_LIST, storageProductList);
    }

    @Test
    public void update() {
        Storage updated = new Storage(STORAGE);
        updated.setName("New storage name");
        storageService.update(updated.getStorageId(), updated, USER_ID);
        MATCHER.assertEquals(updated, storageService.get(STORAGE_ID, USER_ID));
    }

    @Test
    public void create() {
        Storage newStorage = new Storage("newStorage", USER, new HashSet<>());
        Storage created = storageService.create(newStorage, USER_ID);
        newStorage.setStorageId(created.getStorageId());
        Set<Storage> storageSet=storageService.getAll(USER_ID);
        MATCHER.assertListEquals(Arrays.asList(STORAGE, newStorage), new ArrayList<>(storageSet));
    }
}