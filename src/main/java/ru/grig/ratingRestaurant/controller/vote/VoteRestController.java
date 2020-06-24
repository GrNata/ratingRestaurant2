package ru.grig.ratingRestaurant.controller.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.service.VoteService;

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

    public Vote get(long id) {
        return  voteService.get(id);
    }

    public void delete(long id) {
        voteService.delete(id);
    }

    public List<Vote> getAll(){
        return voteService.getAll();
    }

    public Vote create(Vote vote) {
        checkNew(vote);
        log.info("vote {}", vote);
        return voteService.create(vote);
//        return (vote.getVoteTime().isAfter(LocalTime.of(11, 00)) &&
//                vote.getVoteDate().equals(LocalDate.now())) ?
//                voteService.create(vote) : null;
    }

    public Long update(Vote vote) {
//        if(vote.getVoteTime().isAfter(getTimeBefore()) &&
//                vote.getVoteDate().equals(LocalDate.now())) {
//            voteService.update(vote);
//            return true;
//        }
        return voteService.update(vote);
    }


}
