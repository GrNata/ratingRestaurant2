package ru.grig.ratingRestaurant;

//import ru.grig.ratingRestaurant.model.AbstractBaseEntity.*;

import ru.grig.ratingRestaurant.model.Restaurant;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int START_REST_SEQ = 100003;
    public static final int NOT_FOUNR_ID = 10;
    public static final int REST_ID_1 = START_REST_SEQ;
//    public static final long REST_ID_2 = START_REST_SEQ+1;
//    public static final long REST_ID_3 = START_REST_SEQ+2;

    public static final Restaurant REST_1 = new Restaurant(REST_ID_1, "MD", 3, 0);
    public static final Restaurant REST_2 = new Restaurant(REST_ID_1+1, "Burger", 3, 0);
    public static final Restaurant REST_3 = new Restaurant(REST_ID_1+2, "Cafe", 3, 0);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Name", 3, 0);
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(REST_1);
        updated.setName("Updated Name");
        return updated;
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
//        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
        assertThat(actual).isEqualTo(expected);
    }
}
