package ru.grig.ratingRestaurant.controller.web.json;

import org.junit.jupiter.api.Test;
import ru.grig.ratingRestaurant.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static ru.grig.ratingRestaurant.UserTestData.*;

class JsonUtilTest {

    @Test
    void readValues() {
    }

    @Test
    void readValue() throws Exception {
        String json = JsonUtil.writeValues(USER_1);
        System.out.println(json);
        User user = JsonUtil.readValue(json, User.class);
        USER_MATCHER.assertMatch(user, USER_1);
    }

    @Test
    void writeValues() {
    }
}