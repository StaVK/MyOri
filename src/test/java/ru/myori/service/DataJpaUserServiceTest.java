package ru.myori.service;

import org.springframework.test.context.ActiveProfiles;

import static ru.myori.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
/*    @Test
    public void testGet() throws Exception {
        User admin = service.getWithMeals(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, admin);
//        MealTestData.MATCHER.assertListEquals(Arrays.asList(MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1), admin.getMeals());
    }*/


}