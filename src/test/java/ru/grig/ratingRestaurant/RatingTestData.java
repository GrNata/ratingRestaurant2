package ru.grig.ratingRestaurant;

import ru.grig.ratingRestaurant.model.Rating;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.grig.ratingRestaurant.RestaurantTestData.*;


public class RatingTestData {

    public static TestMatcher<Rating> RATING_MATCHER = TestMatcher.usingFieldsComparator("restaurant");

    public static final int NOT_FOUNR_ID = 10;
    public static final int RATING_ID = 100015;
    public static final LocalDate RATING_DATE = LocalDate.of(2020, Month.MARCH, 30);
    public static final int RATING_VOTE = 27;
    public static final int RATING_VOTE_INCREMENT = 76;
    public static final int RATING_VOTE_FOR_DAY = 13;
    public static final LocalDate RATING_DATE_NOT_FOUND = LocalDate.of(2020, Month.JANUARY, 30);

    public static final Rating RATING_1 = new Rating(RATING_ID, REST_1, 12, LocalDate.of(2020, Month.MARCH, 30));
    public static final Rating RATING_2 = new Rating(RATING_ID +1, REST_2, 20, LocalDate.of(2020, Month.MARCH, 30));
    public static final Rating RATING_3 = new Rating(RATING_ID +2, REST_3, 8, LocalDate.of(2020, Month.MARCH, 30));
    public static final Rating RATING_4 = new Rating(RATING_ID +3, REST_1, 10, LocalDate.of(2020, Month.MAY, 10));
    public static final Rating RATING_5 = new Rating(RATING_ID +4, REST_2, 15, LocalDate.of(2020, Month.MAY, 10));
    public static final Rating RATING_6 = new Rating(RATING_ID +5, REST_3, 250, LocalDate.of(2020, Month.MAY, 10));
    public static final Rating RATING_7 = new Rating(RATING_ID +6, REST_1, 5, LocalDate.of(2020, Month.JUNE, 05));
    public static final Rating RATING_8 = new Rating(RATING_ID +7, REST_2, 22, LocalDate.of(2020, Month.JUNE, 05));
    public static final Rating RATING_9 = new Rating(RATING_ID +8, REST_3, 3, LocalDate.of(2020, Month.JUNE, 05));

    public static Rating getNew() {
        return new Rating(REST_1, 77, LocalDate.of(2020, Month.JUNE, 24));
    }

    public static Rating getNewByDateToday() {
        return new Rating(REST_1, 77, LocalDate.now());
    }

    public static Rating getUpdate() {
        Rating updated = new Rating(RATING_ID, REST_1, 77, LocalDate.of(2020, Month.MARCH, 30));
//        updated.setCountVote(77);
        return updated;
    }

//    public static void assertMatch(Rating actual, Rating expexted) {
//        assertThat(actual).isEqualTo(expexted);
//    }
//
//    public static void assertMatch(Iterable<Rating> actual, Rating... expected) {
//        assertMatch(actual, Arrays.asList(expected));
//    }
//
//    public static void assertMatch(Iterable<Rating> actual, Iterable<Rating> expected) {
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    public static void assertMatch(int actual, int expected) {
//        assertThat(actual).isEqualTo(expected);
//    }
}
