package ru.grig.ratingRestaurant.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.ActiveDbProfileResolver;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.UserRepository;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.grig.ratingRestaurant.MenuTestData.NOT_FOUNR_ID;
import static ru.grig.ratingRestaurant.Profiles.*;
import static ru.grig.ratingRestaurant.UserTestData.*;

//@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-db.xml"
//})
//@RunWith(SpringRunner.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ActiveProfiles(resolver = ActiveDbProfileResolver.class, {POSTGRES_DB, JDBC})

public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    private static final StringBuilder results = new StringBuilder();
    private static final Logger log = getLogger("result");

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    @Autowired
    UserService service;

//    @Autowired
//    private CacheManager cacheManager;
//
//    @Before
//    public void setUp() throws Exception {
//        cacheManager.getCache("users").clear();
//    }

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }


    @Test
    public void create() throws Exception {
        User created = service.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void get() throws Exception{
        USER_MATCHER.assertMatch(service.get(USER_ID), USER_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
//        assertFalse(service.delete(USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
    }

    @Test
    public void getAll() throws Exception{
        List<User> all = service.getAll();
        System.out.println("ALL: "+all);
        USER_MATCHER.assertMatch(all, USER_2, USER_1, USER_3);
    }

    @Test
    public void update() {
        User updated = getUpdate();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdate());
    }

    @Test
    public void getByEmail() throws Exception {
        USER_MATCHER.assertMatch(service.getByEmail(USER_EMAIL), USER_2);
    }

    @Test
    public void getByEmailNotFound() throws Exception {
        assertNull(service.getByEmail("WRONG@mail.ru"));
    }
}