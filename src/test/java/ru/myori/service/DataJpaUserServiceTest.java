package ru.myori.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.myori.model.User;
import ru.myori.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.myori.Profiles.DATAJPA;
import static ru.myori.UserTestData.ADMIN;
import static ru.myori.UserTestData.ADMIN_ID;
import static ru.myori.UserTestData.MATCHER;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
/*    @Test
    public void testGet() throws Exception {
        User admin = service.getWithMeals(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, admin);
//        MealTestData.MATCHER.assertListEquals(Arrays.asList(MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1), admin.getMeals());
    }*/


}