package ru.myori.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.myori.model.Role;
import ru.myori.model.User;
import ru.myori.util.exception.NotFoundException;

import java.util.*;

import static ru.myori.testData.UserTestData.*;


public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        MATCHER.assertEquals(ADMIN, user);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(USER_ID));
    }

    @Test
    public void testEnable() {
        service.enable(USER_ID, false);
        Assert.assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        Assert.assertTrue(service.get(USER_ID).isEnabled());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER21_ID);
        List<User> userList = Arrays.asList(ADMIN, USER, USER11, USER2);
        MATCHER.assertListEquals(userList, service.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> all = service.getAll();
        MATCHER.assertListEquals(Arrays.asList(ADMIN, USER, USER11, USER2, USER21), all);
    }

    @Test
    public void testCreate() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(ADMIN, newUser, USER, USER11, USER2, USER21), service.getAll());
    }




}