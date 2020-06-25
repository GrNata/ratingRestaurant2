package ru.grig.ratingRestaurant;

import ru.grig.ratingRestaurant.model.Menu;
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

    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator();

    public static final long NOT_FOUNR_ID = 10;
    public static final long VOTE_ID = 100000;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, USER_ID,  REST_ID_1+2, LocalDate.of(2020, Month.JUNE, 5));
    public static final Vote VOTE_2 = new Vote(VOTE_ID+1, USER_ID+1, REST_ID_1+1, LocalDate.of(2020, Month.JUNE, 5));
    public static final Vote VOTE_3 = new Vote(VOTE_ID+2, USER_ID+2, REST_ID_1, LocalDate.of(2020, Month.JUNE, 5));

    public static Vote getNew() {
        return new Vote(null, USER_ID, REST_ID_1, LocalDate.now());
    }

    public static Vote getUpdate() {
        Vote update = new Vote(VOTE_1);
        update.setIdRestaurant(REST_ID_1);
        update.setIdUser(USER_ID+1);
        return update;
    }

    public static List<Vote> getByUserTestData() {
        List<Vote> list = new ArrayList<>();
        list.add(VOTE_1);
        return list;
    }


}
