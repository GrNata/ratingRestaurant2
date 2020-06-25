package ru.grig.ratingRestaurant.service;

import org.springframework.stereotype.Service;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Rating> all = ratingRepository.getAll();
        int vote = 0;
        boolean isRest = true;

        for (Rating rating : all) {
            if (rating.getDateVote().equals(date) && rating.getIdRestaurant() == idRestaurant) {
                vote = rating.getCountVote();
                vote++;
                rating.setCountVote(vote);
                ratingRepository.save(rating);
                isRest = false;
                break;
            }
        }
        if (isRest){
            vote++;
            ratingRepository.save(new Rating(idRestaurant, vote, date));
        }
    }

//  Подсчет голосов у ресторана
    public int getRatingByRestaurant(Long idRestaurant) {
        List<Rating> all = ratingRepository.getAll();
        int vote = 0;
        int voteAll = 0;
        for (Rating rat : all) {
            voteAll = voteAll + rat.getCountVote();
            if (rat.getIdRestaurant() == idRestaurant){
                vote = vote + rat.getCountVote();
            }
        }
        return vote;
    }

//  удаление голоса добавленного в этот день
    public void incrementVote(Long idRest){
        Rating rating = getByIdRest(idRest);
        if (rating != null) {
            int vote = rating.getCountVote() - 1;
            rating.setCountVote(vote);
            ratingRepository.save(rating);
        }
    }

    private Rating getByIdRest(long idRest) {
        List<Rating> all = ratingRepository.getAll();
        for (Rating r : all) {
            if (r.getIdRestaurant() == idRest && r.getDateVote().equals(LocalDate.now())) {
                return r;
            }
        }
        return null;
    }

    public List<Rating>  getAllByDate(LocalDate date) {
        List<Rating> ratingList = ratingRepository.getAll();
        return ratingList.stream()
                .filter(r -> r.getDateVote().equals(date))
                .collect(Collectors.toList());
    }
}
