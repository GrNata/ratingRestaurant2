package ru.grig.ratingRestaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grig.ratingRestaurant.controller.web.SecurityUtil;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.UserRepository;
import ru.grig.ratingRestaurant.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.grig.ratingRestaurant.util.ValidationUtil.checkNotFoundWithId;
import static ru.grig.ratingRestaurant.controller.web.SecurityUtil.*;

@Service
public class VoteService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote create(Vote vote) {
        log.info("create user: {}", vote);
        System.out.println("vote.time = "+vote.getVoteTime()+"   getTime = "+getTimeBefore()+"  boolean = "+vote.getVoteTime().isBefore(getTimeBefore()));
        System.out.println("vote.date = "+vote.getVoteDate()+"   Date.now = "+LocalDate.now()+"    boolean = "+vote.getVoteDate().equals(LocalDate.now()));
//        return voteRepository.save(vote);
        return (vote.getVoteTime().isBefore(getTimeBefore()) &&
                vote.getVoteDate().equals(LocalDate.now())) ?
                voteRepository.save(vote) : null;
    }

    public Vote get(long id) {
        return checkNotFoundWithId(voteRepository.get(id), id);
    }

    public void delete(long id) {
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public List<Vote> getAll(){
        return voteRepository.getAll();
    }

    public Long update(Vote vote) {
        Long idRest = null;
//        if (LocalTime.now().isBefore(getTimeBefore())) {
            Vote voteGet = getByUserIdAndDateNow();
//            System.out.println("VoreService update / getByUserIdAndDateNow - " + voteGet);
            if (voteGet != null) {
                idRest = voteGet.getIdRestaurant();
                vote.setId(voteGet.getId());
            }
            voteRepository.save(vote);
//        }
        return idRest;
//        checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    public List<Vote> getByUser(Vote vote) {
        List<Vote> voteList = new ArrayList<>();
        List<Vote> votes = voteRepository.getAll();
        for (Vote v : votes){
            if (v.getIdUser() == authUserId()){
                voteList.add(v);
            }
        }
        return voteList;
    }

    private Vote getByUserIdAndDateNow() {
        List<Vote> voteList = getAll();
        for (Vote vote : voteList){
//            System.out.println("VOTE getDate = "+vote.getVoteDate()+"  date now = "+LocalDate.now());
            if (vote.getIdUser() == authUserId() && vote.getVoteDate().equals(LocalDate.now())) {
                return vote;
            }
        }
        return null;
    }
}
