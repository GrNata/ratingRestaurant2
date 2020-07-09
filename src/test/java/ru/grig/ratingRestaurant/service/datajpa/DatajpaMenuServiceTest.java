package ru.grig.ratingRestaurant.service.datajpa;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.service.AbstractServiceTest;
import ru.grig.ratingRestaurant.service.MenuService;
import ru.grig.ratingRestaurant.service.UserService;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.grig.ratingRestaurant.MenuTestData.*;
import static ru.grig.ratingRestaurant.MenuTestData.*;
import static ru.grig.ratingRestaurant.Profiles.*;


@ActiveProfiles({POSTGRES_DB, DATAJPA})
//@ActiveProfiles({HSQL_DB, DATAJPA})
public class DatajpaMenuServiceTest extends AbstractServiceTest {

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
    MenuService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("menu").clear();
    }

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
        Menu created = service.create(getNew(), MENU_ID_REST);
        Integer newId = created.getId();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(service.get(newId, MENU_ID_REST), newMenu);
    }

    @Test
    public void get() throws Exception {
        Menu menu = service.get(MENU_ID, MENU_ID_REST);
        MENU_MATCHER.assertMatch(menu, MENU_1_1);
    }

    @Test
    public void getNotFoundException() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID, MENU_ID_REST));
        assertThrows(NotFoundException.class, () -> service.get(MENU_ID, NOT_FOUNR_ID));
    }

    @Test
    public void delete() {
        service.delete(MENU_ID, MENU_ID_REST);
//        assertFalse(repository.delete(MENU_ID, MENU_ID_REST));
        assertThrows(NotFoundException.class, () -> service.get(MENU_ID, MENU_ID_REST));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID, MENU_ID_REST));
        assertThrows(NotFoundException.class, () -> service.delete(MENU_ID, NOT_FOUNR_ID));
    }

    @Test
    public void getAll() {
        List<Menu> all = service.getAll();
        MENU_MATCHER.assertMatch(all, MENU_1_1, MENU_1_2, MENU_1_3, MENU_2_1, MENU_2_2, MENU_2_3, MENU_3_1, MENU_3_2, MENU_3_3);
    }

    @Test
    public void update() {
        Menu updated = getUpdated();
        service.update(updated, MENU_ID_REST);
        MENU_MATCHER.assertMatch(service.get(MENU_ID, MENU_ID_REST), getUpdated());
    }

    @Test
    public void getAllByRestaurant() {
        List<Menu> all = service.getAllByRestaurant(MENU_ID_REST);
//        System.out.println("ALL:");
//        for (Menu m : all) {
//            System.out.println(m.getId()+" - "+m.getRestaurant().getName()+" - "+m.getDishes());
//        }
        MENU_MATCHER.assertMatch(all, MENU_1_1, MENU_1_2, MENU_1_3);
    }

    @Test
    public void getAllByRestaurantNotFound() throws Exception {
        assertNull(service.getAllByRestaurant(NOT_FOUNR_ID));
    }

}
