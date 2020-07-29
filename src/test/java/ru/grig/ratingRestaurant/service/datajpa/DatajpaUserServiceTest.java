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
import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.service.AbstractServiceTest;
import ru.grig.ratingRestaurant.service.AbstractUserServiceTest;
import ru.grig.ratingRestaurant.service.UserService;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.grig.ratingRestaurant.MenuTestData.NOT_FOUNR_ID;
import static ru.grig.ratingRestaurant.Profiles.DATAJPA;
import static ru.grig.ratingRestaurant.Profiles.POSTGRES_DB;
import static ru.grig.ratingRestaurant.UserTestData.*;

//@ActiveProfiles({POSTGRES_DB, DATAJPA})
@ActiveProfiles({POSTGRES_DB})
class DatajpaUserServiceTest extends AbstractServiceTest {

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
    protected UserService service;

//    @Autowired
//    private CacheManager cacheManager;

//    @Before
//    public void setUp() throws Exception {
//        cacheManager.getCache("users").clear();
//    }

//    @AfterClass
//    public static void printResult() {
//        log.info("\n---------------------------------" +
//                "\nTest                 Duration, ms" +
//                "\n---------------------------------" +
//                results +
//                "\n---------------------------------");
//    }


    @Test
    void create() throws Exception {
        System.out.println("SERVICE = "+service);

        User created = service.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void get() throws Exception{
        USER_MATCHER.assertMatch(service.get(USER_ID), USER_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID));
    }

    @Test
    void delete() throws Exception {
        service.delete(USER_ID);
//        assertFalse(service.delete(USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
    }

    @Test
    void getAll() throws Exception{
        List<User> all = service.getAll();
        System.out.println("ALL: "+all);
        USER_MATCHER.assertMatch(all, USER_2, USER_1, USER_3);
    }

    @Test
    void update() {
        User updated = getUpdate();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdate());
    }

    @Test
    void getByEmail() throws Exception {
        USER_MATCHER.assertMatch(service.getByEmail(USER_EMAIL), USER_2);
    }

    @Test
    void getByEmailNotFound() throws Exception {
        assertNull(service.getByEmail("WRONG@mail.ru"));
    }

}
