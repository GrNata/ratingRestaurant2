package ru.grig.ratingRestaurant.service;

import org.junit.jupiter.api.Test;

//import org.junit.AfterClass;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.internal.runners.statements.ExpectException;
//import org.junit.rules.Stopwatch;
//import org.junit.runner.Description;
//import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.ActiveDbProfileResolver;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.grig.ratingRestaurant.MenuTestData.*;
import static ru.grig.ratingRestaurant.RestaurantTestData.REST_ID_1;

//import static org.junit.Assert.*;

//@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-db.xml"
//})
//@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class AbstractMenuServiceTest extends AbstractServiceTest{
    private static final Logger log = getLogger("result");

    private static final StringBuilder results = new StringBuilder();

//    @Rule
//    public Stopwatch stopwatch = new Stopwatch() {
//        @Override
//        protected void finished(long nanos, Description description) {
//            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
//
//            results.append(result);
//            log.info(result + " ms\n");
//        }
//    };
//
//    @AfterClass
//    public static void printResult() {
//        log.info("\n---------------------------------" +
//                "\nTest                 Duration, ms" +
//                "\n---------------------------------" +
//                results +
//                "\n---------------------------------");
//    }

    @Autowired
    MenuService service;
//    @Autowired
//    MenuRepository repository;

    @Test
    void create() throws Exception {
        Menu created = service.create(getNew(), MENU_ID_REST);
        Integer newId = created.getId();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(service.get(newId, MENU_ID_REST), newMenu);
    }

    @Test
    void get() throws Exception {
        Menu menu = service.get(MENU_ID, MENU_ID_REST);
        MENU_MATCHER.assertMatch(menu, MENU_1_1);
    }

    @Test
    void getNotFoundException() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID, MENU_ID_REST));
        assertThrows(NotFoundException.class, () -> service.get(MENU_ID, NOT_FOUNR_ID));
    }

    @Test
    void delete() {
        service.delete(MENU_ID, MENU_ID_REST);
//        assertFalse(repository.delete(MENU_ID, MENU_ID_REST));
//        assertFalse(service.delete(MENU_ID, MENU_ID_REST));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID, MENU_ID_REST));
        assertThrows(NotFoundException.class, () -> service.delete(MENU_ID, NOT_FOUNR_ID));
    }

    @Test
    void getAll() {
        List<Menu> all = service.getAll();
        MENU_MATCHER.assertMatch(all, MENU_1_1, MENU_1_2, MENU_1_3, MENU_2_1, MENU_2_2, MENU_2_3, MENU_3_1, MENU_3_2, MENU_3_3);
    }

    @Test
    void update() {
        Menu updated = getUpdated();
        service.update(updated, MENU_ID_REST);
        MENU_MATCHER.assertMatch(service.get(MENU_ID, MENU_ID_REST), getUpdated());
    }

    @Test
    void getAllByRestaurant() {
        List<Menu> all = service.getAllByRestaurant(MENU_ID_REST);
//        System.out.println("ALL:");
//        for (Menu m : all) {
//            System.out.println(m.getId()+" - "+m.getRestaurant().getName()+" - "+m.getDishes());
//        }
        MENU_MATCHER.assertMatch(all, MENU_1_1, MENU_1_2, MENU_1_3);
    }

    @Test
    void getAllByRestaurantNotFound() throws Exception {
        assertNull(service.getAllByRestaurant(NOT_FOUNR_ID));
    }
}