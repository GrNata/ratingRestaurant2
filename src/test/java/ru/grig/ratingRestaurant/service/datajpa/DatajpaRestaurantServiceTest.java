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
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.service.AbstractServiceTest;
import ru.grig.ratingRestaurant.service.RestaurantService;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.grig.ratingRestaurant.Profiles.DATAJPA;
import static ru.grig.ratingRestaurant.Profiles.POSTGRES_DB;
import static ru.grig.ratingRestaurant.RestaurantTestData.*;
import static ru.grig.ratingRestaurant.RestaurantTestData.REST_ID_1;

@ActiveProfiles({POSTGRES_DB, DATAJPA})
public class DatajpaRestaurantServiceTest extends AbstractServiceTest {


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
    private RestaurantService restaurantService;


    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
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
        Restaurant newRestaurant = getNew();
        Restaurant created = restaurantService.create(newRestaurant);
        System.out.println("CREATED: "+created);
        Integer newKey = created.getId();
        newRestaurant.setId(newKey);
        System.out.println("NEW_REST: "+newRestaurant);
        assertMatch(created, newRestaurant);
        assertMatch(restaurantService.get(newKey), newRestaurant);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = restaurantService.get(REST_ID_1);
        System.out.println("GET: "+restaurant);
        assertMatch(restaurant, REST_1);
    }

    @Test
    public void getNotFoundException() throws Exception {
        assertThrows(NotFoundException.class, () -> restaurantService.get(NOT_FOUNR_ID));
    }

    @Test
    public void delete() throws Exception {
        restaurantService.delete(REST_ID_1);
        assertThrows(NotFoundException.class, () -> restaurantService.delete(REST_ID_1));
//        assertFalse(restaurantRepository.delete(REST_ID_1));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> restaurantService.get(NOT_FOUNR_ID));
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> all = restaurantService.getAll();
        assertMatch(all, REST_1, REST_2, REST_3);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        restaurantService.update(updated);
        assertMatch(restaurantService.get(REST_ID_1), updated);
    }
}
