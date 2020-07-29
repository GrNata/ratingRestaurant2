package ru.grig.ratingRestaurant.controller;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import ru.grig.ratingRestaurant.RatingTestData;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.util.RestaurantUtil;
import ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.grig.ratingRestaurant.RestaurantTestData.RESTAURANT_LIST_WITH_RATING_MATCHER;
import static ru.grig.ratingRestaurant.UserTestData.*;
import static ru.grig.ratingRestaurant.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class RootControllerTest extends AbstractControllerTest {

//    @Test
//    void root() {
//    }

    @Test
    void getUsers() throws Exception {
        perform(get("/users"))
            .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, USER_2, USER_1, USER_3);
                            }
                        }
                ));
    }

    @Test
    void getRestaurant() throws Exception {
        perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("restaurants"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/restaurants.jsp"))
                .andExpect(model().attribute("rest",
                        new AssertionMatcher<List<RestaurantWithRating>>() {
                            @Override
                            public void assertion(List<RestaurantWithRating> actual) throws AssertionError {
                                RESTAURANT_WITH_RATING_MATCHER.assertMatch(actual,
                                        RestaurantUtil.getRestaurantByRating(RESTAURANTS, RatingTestData.RATINGS));
                            }
                        }));
    }

    @Test
    void getRestaurantList() throws Exception {
        perform(get("/restaurantList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("restaurantList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/restaurantList.jsp"))
                .andExpect(model().attribute("rest",
                        new AssertionMatcher<List<RestaurantWithRating>>() {
                            @Override
                            public void assertion(List<RestaurantWithRating> actual) throws AssertionError {
                                RESTAURANT_LIST_WITH_RATING_MATCHER.assertMatch(actual,
                                        RestaurantUtil.getRestaurantByRating(RESTAURANTS, RatingTestData.RATINGS));
                            }
                        }));
    }
}