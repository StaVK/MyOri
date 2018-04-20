package ru.myori.web;

import org.junit.Test;
import ru.myori.AuthorizedUser;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.myori.TestUtil.userAuth;
import static ru.myori.TestUtil.userHttpBasic;
import static ru.myori.model.AbstractBaseEntity.START_SEQ;
import static ru.myori.testData.ProductTestData.PRODUCT1;
import static ru.myori.testData.ProductTestData.PRODUCT1_ID;
import static ru.myori.testData.UserTestData.ADMIN;
import static ru.myori.testData.UserTestData.USER;


public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {

        mockMvc.perform(get("/users")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(5)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testProducts() throws Exception {
        mockMvc.perform(get("/products")
                .with(userAuth(ADMIN)))
                .andDo(print())
//                .andExpect(status().isOk())
                .andExpect(view().name("products"))
//                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("products", hasSize(8)))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("prodId", is(PRODUCT1_ID)),
                                hasProperty("description", is(PRODUCT1.getDescription()))
                        )
                )));

    }
}