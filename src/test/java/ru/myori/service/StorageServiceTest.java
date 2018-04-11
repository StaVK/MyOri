package ru.myori.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static ru.myori.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class StorageServiceTest extends AbstractServiceTest {

    @Autowired
    protected StorageService storageService;

    @Test
    public void get() {
//        Storage actual=storageService.get();
//        MATCHER.assertEquals(ADMIN_MEAL1, actual);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getProducts() {
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }
}