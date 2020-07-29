package ru.grig.ratingRestaurant.service;
//
////import org.junit.Test;
////import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.support.DataAccessUtils;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.SqlConfig;
//import org.springframework.test.context.junit4.SpringRunner;
//import ru.grig.ratingRestaurant.ActiveDbProfileResolver;
//import ru.grig.ratingRestaurant.model.Rating;
//import ru.grig.ratingRestaurant.repository.RatingRepository;
//import ru.grig.ratingRestaurant.util.exception.NotFoundException;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.List;
//
////import static org.junit.Assert.*;
//import static ru.grig.ratingRestaurant.RatingTestData.*;
//import static ru.grig.ratingRestaurant.RatingTestData.NOT_FOUNR_ID;
//import static ru.grig.ratingRestaurant.RestaurantTestData.REST_1;
//import static ru.grig.ratingRestaurant.RestaurantTestData.REST_ID_1;
//
//@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-db.xml"
//})
////@RunWith(SpringRunner.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class RatingServiceTest {
//
//    @Autowired
//    RatingService service;
//    @Autowired
//    RatingRepository repository;
//
//    @Test
//    public void create() throws Exception{
//        Rating created = service.create(getNew(), REST_ID_1);
//        Integer newId = created.getId() ;
//        Rating newRating = getNew();
//        newRating.setId(newId);
//        RATING_MATCHER.assertMatch(created, newRating);
//        RATING_MATCHER.assertMatch(service.get(newId), newRating);
//    }
//
//    @Test
//    public void get() throws Exception {
//        Rating rating = service.get(RATING_ID);
//        RATING_MATCHER.assertMatch(rating, RATING_1);
//    }
//
//    @Test
//    public void getNotFound() throws Exception {
//        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUNR_ID));
//    }
//
//    @Test
//    public void delete() throws Exception {
//        service.delete(RATING_ID);
//        assertFalse(repository.delete(RATING_ID));
//    }
//
//    @Test
//    public void deleteNotFound() throws Exception {
//        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
//        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUNR_ID));
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        List<Rating> all = service.getAll();
//        RATING_MATCHER.assertMatch(all, RATING_1, RATING_2, RATING_3, RATING_4, RATING_5, RATING_6, RATING_7, RATING_8, RATING_9);
//    }
//
////    @Test
////    public void update() {
////        Rating updated = getUpdate();
////        service.update(updated, REST_ID_1);
////        RATING_MATCHER.assertMatch(service.get(RATING_ID), getUpdate());
////    }
//
//    @Test
//    public void getAllByDate() throws Exception {
//        List<Rating> ratingList = service.getAllByDate(RATING_DATE_TIME);
////        RATING_MATCHER.assertMatch(ratingList, RATING_1, RATING_2, RATING_3);
//        RATING_MATCHER.assertMatch(ratingList, RATING_LIST);
//    }
//
//    @Test
//    public void setByVote() throws Exception {
////        service.setByVote(RATING_ID, RATING_DATE);
////        service.setByVote(REST_ID_1, RATING_DATE);
//        service.setByVote(REST_ID_1, RATING_DATE_TIME);
//        RATING_MATCHER.assertMatch(service.get(RATING_ID).getCountVote(), RATING_VOTE_FOR_DAY);
//    }
//
//    @Test
//    public void setByVoteNotChange() throws Exception {
////        service.setByVote(RATING_ID, RATING_DATE);
//        assertNotEquals(service.get(RATING_ID).getCountVote(), RATING_VOTE_FOR_DAY);
////        assertThrows(NotFoundException.class, () -> service.setByVote(NOT_FOUNR_ID, RATING_DATE));
////        assertThrows(NotFoundException.class, () -> service.setByVote(RATING_ID, RATING_DATE_NOT_FOUND));
//    }
//
//    @Test
//    public void getAllByRestaurant() throws Exception {
//        List<Rating> ratingList = service.getAllRatingByRestaurant(REST_ID_1);
//        RATING_MATCHER.assertMatch(ratingList, RATING_1, RATING_4, RATING_7);
//    }
//
////    @Test
////    public void getAllByRestaurantNotFound() throws Exception {
////        System.out.println("ALL TEST: "+service.getAllByRestaurant(NOT_FOUNR_ID));
////        assertThrows(NotFoundException.class, () -> service.getAllByRestaurant(NOT_FOUNR_ID));
////    }
//
//    @Test
//    public void getRatingByRestaurant() throws Exception {
////        int vote = service.getRatingByRestaurant(RATING_ID);
//        int vote = service.getRatingByRestaurant(REST_ID_1);
//        RATING_MATCHER.assertMatch(vote, RATING_VOTE);
//    }
//
//    //  подсчет голосов у ресторана
//    @Test
//    public void getRatingByRestaurantNotFound() throws Exception {
//        assertEquals(0, service.getRatingByRestaurant(NOT_FOUNR_ID));
//    }
//
//    @Test
//    public void incrementVote() {
//        Rating newRating = getNewByDateToday();
//        Rating created = service.create(newRating, REST_ID_1);
//        Integer newId = created.getId();
//        newRating.setId(newId);
////        service.incrementVote(RATING_ID);
//        service.incrementVote(REST_ID_1);
////        Rating increment = DataAccessUtils.singleResult(service.getAllByDate(LocalDate.now().atTime(00, 00)));
//        Rating increment = DataAccessUtils.singleResult(service.getAllByDate(LocalDateTime.now()));
//        RATING_MATCHER.assertMatch(increment.getCountVote(), RATING_VOTE_INCREMENT);
//    }
//
//    @Test
//    public void getByRestaurantByDate() throws Exception {
//        LocalDate date = LocalDate.of(2020, Month.MARCH, 30);
//        Rating rating = service.getByRestaurantByDate(REST_ID_1, date);
//        RATING_MATCHER.assertMatch(rating, RATING_1);
//    }
//
//    @Test
//    public void getByRestaurantByDateNotFound() throws Exception {
//        LocalDate date = LocalDate.of(2020, Month.MARCH, 30);
//        assertThrows(NotFoundException.class, () -> service.getByRestaurantByDate(NOT_FOUNR_ID, date));
//    }
//
//    @Test
//    public void getByRestaurantByWrongDate() throws Exception {
//        assertThrows(NotFoundException.class, () -> service.getByRestaurantByDate(REST_ID_1, RATING_DATE_NOT_FOUND));
//    }
}