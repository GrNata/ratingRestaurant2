package ru.grig.ratingRestaurant.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.Stopwatch;
//import org.junit.runner.Description;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.service.AbstractServiceTest;
import ru.grig.ratingRestaurant.service.MenuService;
import ru.grig.ratingRestaurant.service.RatingService;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import static org.junit.Assert.*;
//import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.grig.ratingRestaurant.Profiles.*;
import static ru.grig.ratingRestaurant.RatingTestData.*;
import static ru.grig.ratingRestaurant.RestaurantTestData.REST_ID_1;

@ActiveProfiles({POSTGRES_DB})
//@ActiveProfiles({POSTGRES_DB, DATAJPA})
//@ActiveProfiles({HSQL_DB, DATAJPA})
class DatajpaRatingServiceTest extends AbstractServiceTest {

    private static final StringBuilder results = new StringBuilder();
    private static final Logger log = getLogger("result");

//    @Rule
//    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
//    public final Stopwatch stopwatch = new Stopwatch() {
//        @Override
//        protected void finished(long nanos, Description description) {
//            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
//            results.append(result);
//            log.info(result + " ms\n");
//        }
//    };

    @Autowired
    protected RatingService service;

//    @Autowired
//    private CacheManager cacheManager;

//    @Before
//    public void setUp() throws Exception {
//        cacheManager.getCache("rating").clear();
//    }
//
//    @AfterClass
//    public static void printResult() {
//        log.info("\n---------------------------------" +
//                "\nTest                 Duration, ms" +
//                "\n---------------------------------" +
//                results +
//                "\n---------------------------------");
//    }

    @Test
    void create() throws Exception{
        Rating created = service.create(getNew(), REST_ID_1);
        Integer newId = created.getId() ;
        Rating newRating = getNew();
        newRating.setId(newId);
        RATING_MATCHER.assertMatch(created, newRating);
        RATING_MATCHER.assertMatch(service.get(newId), newRating);
    }

    @Test
    void get() throws Exception {
        Rating rating = service.get(RATING_ID);
        RATING_MATCHER.assertMatch(rating, RATING_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID));
    }

    @Test
    void delete() throws Exception {
        service.delete(RATING_ID);
//        assertFalse(repository.delete(RATING_ID));
        assertThrows(NotFoundException.class, () -> service.get(RATING_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
    }

    @Test
    void getAll() throws Exception {
        List<Rating> all = service.getAll();
        RATING_MATCHER.assertMatch(all, RATING_1, RATING_2, RATING_3, RATING_4, RATING_5, RATING_6, RATING_7, RATING_8, RATING_9);
    }

    @Test
    void update() {
        Rating updated = getUpdate();
        service.update(updated, REST_ID_1);
        RATING_MATCHER.assertMatch(service.get(RATING_ID), getUpdate());
    }

    @Test
    void getAllByDate() throws Exception {
        List<Rating> ratingList = service.getAllByDate(RATING_DATE_TIME);
//        RATING_MATCHER.assertMatch(ratingList, RATING_1, RATING_2, RATING_3);
        RATING_MATCHER.assertMatch(ratingList, RATING_LIST);
    }

    @Test
    void setByVote() throws Exception {
//        service.setByVote(RATING_ID, RATING_DATE);
//        service.setByVote(REST_ID_1, RATING_DATE);
        service.setByVote(REST_ID_1, RATING_DATE_TIME);
        RATING_MATCHER.assertMatch(service.get(RATING_ID).getCountVote(), RATING_VOTE_FOR_DAY);
    }

    @Test
    void setByVoteNotChange() throws Exception {
//        service.setByVote(RATING_ID, RATING_DATE);
        assertNotEquals(service.get(RATING_ID).getCountVote(), RATING_VOTE_FOR_DAY);
//        assertThrows(NotFoundException.class, () -> service.setByVote(NOT_FOUNR_ID, RATING_DATE));
//        assertThrows(NotFoundException.class, () -> service.setByVote(RATING_ID, RATING_DATE_NOT_FOUND));
    }

    @Test
    void getAllByRestaurant() throws Exception {
        List<Rating> ratingList = service.getAllRatingByRestaurant(REST_ID_1);
        RATING_MATCHER.assertMatch(ratingList, RATING_1, RATING_4, RATING_7);
    }

//    @Test
//    public void getAllByRestaurantNotFound() throws Exception {
//        System.out.println("ALL TEST: "+service.getAllByRestaurant(NOT_FOUNR_ID));
//        assertThrows(NotFoundException.class, () -> service.getAllByRestaurant(NOT_FOUNR_ID));
//    }

    @Test
    void getRatingByRestaurant() throws Exception {
//        int vote = service.getRatingByRestaurant(RATING_ID);
        int vote = service.getRatingByRestaurant(REST_ID_1);
        RATING_MATCHER.assertMatch(vote, RATING_VOTE);
    }

    //  подсчет голосов у ресторана
    @Test
    void getRatingByRestaurantNotFound() throws Exception {
        assertEquals(0, service.getRatingByRestaurant(NOT_FOUNR_ID));
    }

    @Test
    void incrementVote() {
        Rating newRating = getNewByDateToday();
        Rating created = service.create(newRating, REST_ID_1);
        Integer newId = created.getId();
        newRating.setId(newId);
//        service.incrementVote(RATING_ID);
        service.incrementVote(REST_ID_1);
//        Rating increment = DataAccessUtils.singleResult(service.getAllByDate(LocalDate.now().atTime(00, 00)));
        Rating increment = DataAccessUtils.singleResult(service.getAllByDate(LocalDateTime.now()));
        RATING_MATCHER.assertMatch(increment.getCountVote(), RATING_VOTE_INCREMENT);
    }

    @Test
    void getByRestaurantByDate() throws Exception {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 30);
        Rating rating = service.getByRestaurantByDate(REST_ID_1, date);
        RATING_MATCHER.assertMatch(rating, RATING_1);
    }

    @Test
    void getByRestaurantByDateNotFound() throws Exception {
        LocalDate date = LocalDate.of(2020, Month.MARCH, 30);
        assertThrows(NotFoundException.class, () -> service.getByRestaurantByDate(NOT_FOUNR_ID, date));
    }

    @Test
    void getByRestaurantByWrongDate() throws Exception {
        assertThrows(NotFoundException.class, () -> service.getByRestaurantByDate(REST_ID_1, RATING_DATE_NOT_FOUND));
    }

}
