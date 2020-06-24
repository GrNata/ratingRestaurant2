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

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MenuService service;
    @Autowired
    MenuRepository repository;

    @Test
    public void create() throws Exception {
        Menu newMenu = getNew();
        Menu created = service.create(newMenu);
        Long newId = created.getId();
        newMenu.setId(newId);
        assertMatch(created, newMenu);
        assertMatch(service.get(newId), newMenu);
    }

    @Test
    public void get() throws Exception {
        Menu menu = service.get(MENU_ID);
        assertMatch(menu, MENU_1_1);
    }

    @Test
    public void getNotFoundException() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID));
    }

    @Test
    public void delete() {
        service.delete(MENU_ID);
        assertFalse(repository.delete(MENU_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
    }

    @Test
    public void getAll() {
        List<Menu> all = service.getAll();
        assertMatch(all, MENU_1_1, MENU_1_2, MENU_1_3, MENU_2_1, MENU_2_2, MENU_2_3, MENU_3_1, MENU_3_2, MENU_3_3);
    }

    @Test
    public void update() {
        Menu updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(MENU_ID), updated);
    }

    @Test
    public void getAllByRestaurant() {
        List<Menu> all = service.getAllByRestaurant(REST_ID_1);
        assertMatch(all, MENU_1_1, MENU_1_2, MENU_1_3);
    }
}