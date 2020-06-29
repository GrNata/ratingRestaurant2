package ru.grig.ratingRestaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.controller.web.SecurityUtil;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.VoteRepository;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.util.List;

import static ru.grig.ratingRestaurant.VoteTestData.*;
import static ru.grig.ratingRestaurant.UserTestData.USER_ID;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    @Autowired
    VoteService service;
    @Autowired
    VoteRepository repository;

    @Test
    public void create() throws Exception{
        Vote created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Vote newVote = getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId, USER_ID), newVote);
    }

    @Test
    public void get() throws Exception{
        Vote vote = service.get(VOTE_ID, USER_ID);
        VOTE_MATCHER.assertMatch(vote, VOTE_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID, USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(VOTE_ID, NOT_FOUNR_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(VOTE_ID, USER_ID);
        assertFalse(repository.delete(VOTE_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID, USER_ID));
        assertThrows(NotFoundException.class, () -> service.delete(VOTE_ID, NOT_FOUNR_ID));
    }

    @Test
    public void getAll() throws Exception {
        List<Vote> all = service.getAll();
        VOTE_MATCHER.assertMatch(all, VOTE_1, VOTE_2, VOTE_3);
    }

    @Test
    public void getAllByUser() throws Exception {
        List<Vote> all = service.getAllByUser(USER_ID);
        VOTE_MATCHER.assertMatch(all, VOTE_1);
    }

    @Test
    public void getAllByUserNotFound() throws Exception {
        System.out.println("ALL: "+service.getAllByUser(NOT_FOUNR_ID));
        assertNull(service.getAllByUser(NOT_FOUNR_ID));
    }

    @Test       //  может меняться только ресторан
    public void update() throws Exception {
        Vote updated = getUpdate();
        service.update(updated, USER_ID);
        Vote vote = service.get(VOTE_ID, USER_ID);
//        System.out.println("VOTE TEST: "+ vote.getUser()+ "  : "+vote.getRestaurant());
//        System.out.println("VOTE 2 TEST: "+ getUpdate().getUser()+"  :"+getUpdate().getRestaurant());
        VOTE_MATCHER.assertMatch(service.get(VOTE_ID, USER_ID), getUpdate());
    }

//    @Test     //  исключен метод
//    public void getByUser() {
//        List<Vote> getByUsered = getByUserTestData();
//        SecurityUtil.setAuthUserId(USER_ID, UserService);
//        VOTE_MATCHER.assertMatch(getByUsered, service.getByUser(VOTE_1));
//    }
}