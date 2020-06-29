package ru.grig.ratingRestaurant;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.grig.ratingRestaurant.RestaurantTestData.*;
import static ru.grig.ratingRestaurant.UserTestData.*;

public class VoteTestData {

//    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator("users", "restaurant");
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator();

    public static final int NOT_FOUNR_ID = 10;
    public static final int VOTE_ID = 100024;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, USER_ID,  REST_ID_1+2, LocalDate.of(2020, Month.JUNE, 5));
    public static final Vote VOTE_2 = new Vote(VOTE_ID+1, USER_ID+1, REST_ID_1+1, LocalDate.of(2020, Month.JUNE, 5));
    public static final Vote VOTE_3 = new Vote(VOTE_ID+2, USER_ID+2, REST_ID_1, LocalDate.of(2020, Month.JUNE, 5));


//    public static final Vote VOTE_1 = new Vote(VOTE_ID, USER_1,  REST_3, LocalDateTime.of(2020, Month.JUNE, 5,  00, 00));
//    public static final Vote VOTE_2 = new Vote(VOTE_ID+1, USER_2, REST_2, LocalDateTime.of(2020, Month.JUNE, 5, 00, 00));
//    public static final Vote VOTE_3 = new Vote(VOTE_ID+2, USER_3, REST_1, LocalDateTime.of(2020, Month.JUNE, 5, 00, 00));

    public static Vote getNew() {
//        return new Vote(null, USER_1, REST_1, LocalDateTime.now());
        return new Vote(null, USER_ID, REST_ID_1, LocalDate.now());
    }

    public static Vote getUpdate() {
//        Vote update = new Vote(VOTE_ID, USER_1,  REST_3, LocalDateTime.of(2020, Month.JUNE, 5,  00, 00));
        Vote update = new Vote(VOTE_ID, USER_ID,  REST_ID_1+2, LocalDate.of(2020, Month.JUNE, 5));
        update.setIdRestaurant(REST_ID_1);
//        update.setRestaurant(REST_1);
//        update.setIdUser(USER_ID);
        return update;
    }

    public static List<Vote> getByUserTestData() {
        List<Vote> list = new ArrayList<>();
        list.add(VOTE_1);
        list.add(VOTE_2);
        list.add(VOTE_3);
        return list;
    }


}
