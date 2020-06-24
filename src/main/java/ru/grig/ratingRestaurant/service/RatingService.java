package ru.grig.ratingRestaurant.service;

import org.springframework.stereotype.Service;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating get(long id) {
        return checkNotFoundWithId(ratingRepository.get(id), id);
    }

    public void delete(long id) {
        checkNotFoundWithId(ratingRepository.delete(id), id);
    }

    public List<Rating> getAll() {
        return ratingRepository.getAll();
    }

    public void update(Rating rating) {
        checkNotFoundWithId(ratingRepository.save(rating), rating.getId());
    }

    public void setByVote(Long idRestaurant, LocalDate date) {
        //_____________________
//        date = LocalDate.now();
        //_____________________
        int vote = 0;
        boolean isRest = true;
//        for (Map.Entry<Long, Rating> rat : repository.entrySet()) {
//            if (rat.getValue().getDateVote().equals(date) && rat.getValue().getIdRestaurant() == idRestaurant){
//                vote = rat.getValue().getCountVote();
//                vote++;
//                rat.getValue().setCountVote(vote);
//                isRest = false;
//                break;
//            }
//        }
        for (Rating rating : getAll()) {
            if (rating.getDateVote().equals(date) && rating.getIdRestaurant() == idRestaurant) {
//            if (rating.getIdRestaurant() == idRestaurant) {
                vote = rating.getCountVote();
                vote++;
                rating.setCountVote(vote);
                update(rating);
                isRest = false;
                break;
            }
        }
        if (isRest){
            vote++;
            create(new Rating(idRestaurant, vote, date));
        }
    }

    public int getRatingByRestaurant(Long idRestaurant) {
        int vote = 0;
        int voteAll = 0;
        for (Rating rat : getAll()) {
            voteAll = voteAll + rat.getCountVote();
            if (rat.getIdRestaurant() == idRestaurant){
                vote = vote + rat.getCountVote();
            }
        }
        return vote;        //  пока количество голосов, vote/voteAll*100 - процент
    }

    public void incrementVote(Long idRest){
        Rating rating = getByIdRest(idRest);
        if (rating != null) {
            int vote = rating.getCountVote() - 1;
            rating.setCountVote(vote);
        }
    }

    private Rating getByIdRest(long idRest) {
        for (Rating r : getAll()) {
            if (r.getIdRestaurant() == idRest && r.getDateVote().equals(LocalDate.now())) {
                System.out.println("RATING_SERVICE getByIdRest r: "+r);
                return r;
            }
        }
        return null;
    }
}
