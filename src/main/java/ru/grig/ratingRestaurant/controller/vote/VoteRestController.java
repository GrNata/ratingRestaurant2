package ru.grig.ratingRestaurant.controller.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.service.UserService;
import ru.grig.ratingRestaurant.service.VoteService;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;
import static ru.grig.ratingRestaurant.controller.web.SecurityUtil.*;

@Controller
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(VoteRestController.class);

    @Autowired
    VoteService voteService;

    public Vote get(int id, int userId) {
        return  voteService.get(id, userId);
    }

    public void delete(int id, int userId) {
        voteService.delete(id, userId);
    }

    public List<Vote> getAll(){
        return voteService.getAll();
    }
    public List<Vote> getAllByUser(int userId){
        return voteService.getAllByUser(userId);
    }

    public Vote create(Vote vote, int userId) {
        checkNew(vote);
        log.info("vote {}", vote);
        return voteService.create(vote, userId);
    }

    public Integer update(Vote vote, int userId) {
        return voteService.update(vote, userId);
    }

    public Vote getByUserAndDateNow(int userId) {
        List<Vote> voteList = voteService.getAll();
        for (Vote v : voteList){
//            if (v.getIdUser() == userId && v.getVoteDate().equals(LocalDate.now())){
            if (v.getUser().getId() == userId && v.getVoteDate().equals(LocalDate.now())){
                return v;
            }
        }
        return null;
    }

}
