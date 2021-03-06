package ru.grig.ratingRestaurant.controller.rating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.service.RatingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

@Controller
public class RatingRestController {
    private final Logger log = LoggerFactory.getLogger(RatingRestController.class);

    @Autowired
    RatingService ratingService;

//    public Rating get(long id) {
//        log.info("get id {}", id);
//        return ratingService.get(id);
//    }

    public void delete(long id) {
        log.info("delete id {}", id);
    }

    public List<Rating> getAll(){
        log.info("getAll");
        return ratingService.getAll();
    }

    public Rating create(Rating rating, int restId){
        log.info("create rating: {}", rating);
//        checkNew(rating);
        return ratingService.create(rating, restId);
    }

//    public void update(Rating rating){
//        log.info("update rating: {}", rating);
//        ratingService.update(rating);
//    }

//    public void setByVote(Integer idRestaurant, LocalDate date) {
    public void setByVote(Integer idRestaurant, LocalDateTime date) {
        log.info("setByVote");
        ratingService.setByVote(idRestaurant, date);
    }

    public int getRatingByRestaurant(Integer idRestaurant) {
        log.info("getRatingByRestaurant");
        return ratingService.getRatingByRestaurant(idRestaurant);
    }

    public void incrementVote(Integer idRest){
        ratingService.incrementVote(idRest);
    }
}
