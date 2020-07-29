package ru.grig.ratingRestaurant.service;

import org.junit.jupiter.api.Test;

//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.UserRepository;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.grig.ratingRestaurant.MenuTestData.NOT_FOUNR_ID;
import static ru.grig.ratingRestaurant.UserTestData.*;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

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
//        System.out.println("ALL: "+all);
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