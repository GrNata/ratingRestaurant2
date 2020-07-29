package ru.grig.ratingRestaurant.controller.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.grig.ratingRestaurant.controller.web.AbstractControllerTest;
import ru.grig.ratingRestaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.grig.ratingRestaurant.controller.web.user.ProfileRestController.REST_URL;
import static ru.grig.ratingRestaurant.UserTestData.*;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    @Test
    void get() throws Exception {
        System.out.println("TEST !!!");

        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER_1));
    }
}
