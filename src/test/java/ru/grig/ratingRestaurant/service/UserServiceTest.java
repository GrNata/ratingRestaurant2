package ru.grig.ratingRestaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.UserRepository;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.grig.ratingRestaurant.MenuTestData.NOT_FOUNR_ID;
import static ru.grig.ratingRestaurant.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    UserService service;
    @Autowired
    UserRepository repository;


    @Test
    public void create() throws Exception {
        User newUser = getNew();
        User created = service.create(newUser);
        Long newId = created.getId();
        newUser.setId(newId);
        assertMatch(created, newUser);
        assertMatch(service.get(newId), newUser);
    }

    @Test
    public void get() throws Exception{
        assertMatch(service.get(USER_ID), USER_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertFalse(repository.delete(USER_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
    }

    @Test
    public void getAll() throws Exception{
        List<User> all = service.getAll();
        System.out.println("ALL: "+all);
        assertMatch(all, USER_2, USER_1, USER_3);
    }

    @Test
    public void update() {
        User updated = getUpdate();
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }
}