package ru.grig.ratingRestaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.grig.ratingRestaurant.controller.web.SecurityUtil;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.RatingRepository;
import ru.grig.ratingRestaurant.repository.UserRepository;
import ru.grig.ratingRestaurant.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.checkNotFoundWithId;
import static ru.grig.ratingRestaurant.controller.web.SecurityUtil.*;

@Service
public class VoteService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
//        this.ratingService = (RatingService) ratingService;
    }

    @CacheEvict(value = "vote", allEntries = true)
    public Vote create(Vote vote, int userId) {
        log.info("create user: {}", vote);
        Assert.notNull(vote, "Vote must not be NULL");
        User user = userRepository.get(userId);
        return (LocalTime.now().isBefore(getTimeBefore()) &&
                vote.getDate().equals(LocalDate.now())) ?
                voteRepository.save2(vote, user) : null;
    }

    public Vote get(int id, int userId) {
        return voteRepository.get(id, userId);
//        return checkNotFoundWithId(voteRepository.get(id, userId), id);
    }

    @CacheEvict(value = "vote", allEntries = true)
    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    @Cacheable("vote")
    public List<Vote> getAll(){
        return voteRepository.getAll();
    }

    public List<Vote> getAllByUser(int userId) {
        List<Vote> votes = voteRepository.getAllByUser(userId);
        return votes.isEmpty() ? null : votes ; }

    @CacheEvict(value = "vote", allEntries = true)
    public Integer update(Vote vote, int userId) {
        Assert.notNull(vote, "Restaurant must not be NULL");

        if (LocalTime.now().isAfter(SecurityUtil.getTimeBefore())) {
            // если время больше
            return null;
        }

        Integer idRest = null;
//        if (LocalTime.now().isBefore(getTimeBefore())) {
//            Vote voteGet = getByUserIdAndDateNow();
        //  получуние Vote для USER на сегодня
            Vote voteGet = getByDate(userId, LocalDate.now().atTime(00, 00));
        System.out.println("VOTE: "+vote+"   VOTE_GET: "+voteGet);
        //  усли есть, тогда добавляем голос в RAITING и запись USER в VOTE с сегодняшней датой
            if (voteGet != null) {
//                idRest = voteGet.getIdRestaurant();
                idRest = voteGet.getRestaurant().getId();
                vote.setId(voteGet.getId());
            }
//            ratingService.setByVote(vote.getIdRestaurant(), LocalDateTime.now());
        User user = userRepository.get(userId);
            voteRepository.save2(vote, user);
//        }
        return idRest;
//        checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

//    public List<Vote> getByUser(Vote vote) {
//        List<Vote> voteList = new ArrayList<>();
//        List<Vote> votes = voteRepository.getAll();
//        for (Vote v : votes){
//            if (v.getIdUser() == authUserId()){
//                voteList.add(v);
//            }
//        }
//        return voteList;
//    }

    public Vote getByDate(int userId, LocalDateTime dateTime) {
//    private Vote getByUserIdAndDateNow() {
        List<Vote> voteList = getAllByUser(userId);
        for (Vote vote : voteList){
//            System.out.println("VOTE getDate = "+vote.getVoteDate()+"  date now = "+LocalDate.now());
//            if (vote.getIdUser() == authUserId() && vote.getVoteDate().equals(LocalDate.now())) {
            if (vote.getVoteDate().equals(dateTime)) {
                return vote;
            }
        }
        return null;
    }
}
