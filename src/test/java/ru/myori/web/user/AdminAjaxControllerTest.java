package ru.myori.web.user;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.myori.TestUtil;
import ru.myori.model.User;
import ru.myori.web.AbstractControllerTest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.myori.TestUtil.userHttpBasic;
import static ru.myori.testData.UserTestData.*;


public class AdminAjaxControllerTest extends AbstractControllerTest {

    private static final String AJAX_URL = "/ajax/admin/users/";

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(AJAX_URL + ADMIN_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }

    @Test
    public void testGetByEmail() throws Exception {
        mockMvc.perform(get(AJAX_URL + "by?email=" + USER.getEmail())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(AJAX_URL + USER_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        List<User> forTest = new ArrayList<>(USERS_LIST);
        forTest.remove(USER);
        MATCHER.assertListEquals(forTest, userService.getAll());
    }


    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(AJAX_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(USERS_LIST)));
    }
}