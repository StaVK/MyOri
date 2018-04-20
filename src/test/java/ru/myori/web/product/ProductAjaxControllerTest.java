package ru.myori.web.product;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.myori.AuthorizedUser;
import ru.myori.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.myori.TestUtil.userHttpBasic;
import static ru.myori.testData.ProductTestData.*;
import static ru.myori.testData.UserTestData.ADMIN;

public class ProductAjaxControllerTest extends AbstractControllerTest {
    private static final String AJAX_URL = "/ajax/product";

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(AJAX_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(PRODUCT_LIST));
    }
}
