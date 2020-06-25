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
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    VoteService service;
    @Autowired
    VoteRepository repository;

    @Test
    public void create() throws Exception{
        Vote newVote = getNew();
        Vote created = service.create(newVote);
        Long newId = created.getId();
        newVote.setId(newId);
        assertMatch(created, newVote);
        assertMatch(service.get(newId), newVote);
    }

    @Test
    public void get() throws Exception{
        Vote vote = service.get(VOTE_ID);
        assertMatch(vote, VOTE_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(VOTE_ID);
        assertFalse(repository.delete(VOTE_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
    }

    @Test
    public void getAll() throws Exception {
        List<Vote> all = service.getAll();
        assertMatch(all, VOTE_1, VOTE_2, VOTE_3);
    }

    @Test
    public void update() throws Exception {
        Vote updated = getUpdate();
        service.update(updated);
        assertMatch(service.get(VOTE_ID), updated);
    }

    @Test
    public void getByUser() {
        List<Vote> getByUsered = getByUserTestData();
        SecurityUtil.setAuthUserId(USER_ID);
        assertMatch(getByUsered, service.getByUser(VOTE_1));
    }
}