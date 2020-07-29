package ru.grig.ratingRestaurant.service.datajpa;

//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.Stopwatch;
//import org.junit.runner.Description;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.service.AbstractServiceTest;
import ru.grig.ratingRestaurant.service.AbstractVoteServiceTest;
import ru.grig.ratingRestaurant.service.RatingService;
import ru.grig.ratingRestaurant.service.VoteService;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import static org.junit.Assert.*;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.grig.ratingRestaurant.Profiles.DATAJPA;
import static ru.grig.ratingRestaurant.Profiles.POSTGRES_DB;
import static ru.grig.ratingRestaurant.UserTestData.USER_ID;
import static ru.grig.ratingRestaurant.VoteTestData.*;
import static ru.grig.ratingRestaurant.VoteTestData.getForDate;

@ActiveProfiles({POSTGRES_DB, DATAJPA})
public class DatajpaVoteServiceTest extends AbstractVoteServiceTest {
//
//    private static final StringBuilder results = new StringBuilder();
//    private static final Logger log = getLogger("result");
//
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
//
//    @Autowired
//    VoteService service;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Before
//    public void setUp() throws Exception {
//        cacheManager.getCache("vote").clear();
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
//
//    @Test
////    @Transactional
//    public void create() throws Exception{
//        Vote created = service.create(getNew(), USER_ID);
//        Integer newId = created.getId();
//        NEW_VOTE.setId(newId);
//        VOTE_MATCHER.assertMatch(created, NEW_VOTE);
//        VOTE_MATCHER.assertMatch(service.get(newId, USER_ID), NEW_VOTE);
//    }
//
//    @Test
//    public void get() throws Exception{
//        Vote vote = service.get(VOTE_ID, USER_ID);
//        VOTE_MATCHER.assertMatch(vote, VOTE_1);
//    }
//
//    @Test
//    public void getNotFound() throws Exception {
//        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID, USER_ID));
//        assertThrows(NotFoundException.class, () -> service.get(VOTE_ID, NOT_FOUNR_ID));
//        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID, NOT_FOUNR_ID));
//    }
//
//    @Test
//    public void delete() throws Exception {
//        service.delete(VOTE_ID, USER_ID);
////        assertFalse(repository.delete(VOTE_ID, USER_ID));
//        assertThrows(NotFoundException.class, () -> service.get(VOTE_ID, USER_ID));
////        assertNull(service.get(VOTE_ID, USER_ID));
//    }
//
//    @Test
//    public void deleteNotFound() throws Exception {
//        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID, USER_ID));
//        assertThrows(NotFoundException.class, () -> service.delete(VOTE_ID, NOT_FOUNR_ID));
////        assertEquals(false, service.delete(NOT_FOUNR_ID, USER_ID));
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        List<Vote> all = service.getAll();
//        System.out.println("ALL: "+all);
//        VOTE_MATCHER.assertMatch(all, VOTE_1, VOTE_2, VOTE_3);
//    }
//
//    @Test
//    public void getAllByUser() throws Exception {
//        List<Vote> all = service.getAllByUser(USER_ID);
//        VOTE_MATCHER.assertMatch(all, VOTE_1);
//    }
//
//    @Test
//    public void getAllByUserNotFound() throws Exception {
//        System.out.println("ALL: "+service.getAllByUser(NOT_FOUNR_ID));
//        assertNull(service.getAllByUser(NOT_FOUNR_ID));
//    }
//
//    @Test       //  может меняться только ресторан
//    public void update() throws Exception {
//        Vote updated = getUpdate();
//        service.update(updated, USER_ID);
//        Vote vote = service.get(VOTE_ID, USER_ID);
//        VOTE_MATCHER.assertMatch(service.get(VOTE_ID, USER_ID), getUpdate());
//    }
//
//    @Test
//    public void getByDate() {
//        VOTE_MATCHER.assertMatch(service.getByDate(USER_ID, DATE), getForDate());
//    }
}
