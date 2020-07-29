package ru.grig.ratingRestaurant.controller.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ru.grig.ratingRestaurant.controller.web.AbstractControllerTest;
import ru.grig.ratingRestaurant.service.UserService;
import static ru.grig.ratingRestaurant.UserTestData.*;

class AdminRestControllerTest extends AbstractControllerTest {
    private final static String REST_URL = AdminRestController.REST_URL + "/";

    @Autowired
    private UserService userService;

    @Test
    void getAll() {

    }

    @Test
    void get() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID))
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//                .andExpect(USER_MATCHER.contentJson(USER_1));
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

}