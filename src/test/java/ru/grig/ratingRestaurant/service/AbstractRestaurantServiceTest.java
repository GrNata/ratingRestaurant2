package ru.grig.ratingRestaurant.service;

import org.junit.jupiter.api.Test;

//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.RestaurantTestData;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.grig.ratingRestaurant.RestaurantTestData.*;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;

//import static org.junit.Assert.*;

//@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-db.xml"
//})
//@RunWith(SpringRunner.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService restaurantService;

//    @Autowired
//    private RestaurantRepository restaurantRepository;

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = getNew();
        Restaurant created = restaurantService.create(newRestaurant);
        Integer newKey = created.getId();
        newRestaurant.setId(newKey);
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
//        System.out.println(restaurantRepository.delete(REST_ID_1));
//        assertFalse(restaurantRepository.delete(REST_ID_1));
        assertThrows(NotFoundException.class, () -> restaurantService.delete(REST_ID_1));
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