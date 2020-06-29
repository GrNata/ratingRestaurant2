package ru.grig.ratingRestaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;

import static ru.grig.ratingRestaurant.MenuTestData.*;
import static ru.grig.ratingRestaurant.RestaurantTestData.REST_ID_1;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuServiceTest {

    @Autowired
    MenuService service;
    @Autowired
    MenuRepository repository;

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
        assertFalse(repository.delete(MENU_ID, MENU_ID_REST));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID, MENU_ID_REST));
        assertThrows(NotFoundException.class, () -> service.delete(MENU_ID, NOT_FOUNR_ID));
    }

    @Test
    public void getAll() {
        List<Menu> all = service.getAll();
        System.out.println("ALL: "+all);
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
        System.out.println("ALL: "+all);
        MENU_MATCHER.assertMatch(all, MENU_1_1, MENU_1_2, MENU_1_3);
    }

    @Test
    public void getAllByRestaurantNotFound() throws Exception {
        assertNull(service.getAllByRestaurant(NOT_FOUNR_ID));
    }
}