package ru.grig.ratingRestaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.RatingRepository;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;
import ru.grig.ratingRestaurant.repository.datajpa.DatajpaRatingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    public RatingService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Rating create(Rating rating) {
        Assert.notNull(rating, "Restaurant must not be NULL");
        return ratingRepository.save(rating);
    }

    public Rating get(int id) {
        return checkNotFoundWithId(ratingRepository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(ratingRepository.delete(id), id);
    }

    public List<Rating> getAll() {
        return ratingRepository.getAll();
    }

    public void update(Rating rating) {
        Assert.notNull(rating, "Restaurant must not be NULL");
        checkNotFoundWithId(ratingRepository.save(rating), rating.getId());
    }

    public List<Rating> getAllByRestaurant(int idRest) {
        List<Rating> all = ratingRepository.getAllByRestaurant(idRest);
        System.out.println("ALL GET: "+all);
        return all;
    }

    public void setByVote(Integer idRestaurant, LocalDate date) {
        Rating rating = ratingRepository.getByRestaurantByDate(idRestaurant, date);
        if (rating != null) {
            int vote = rating.getCountVote();
            vote++;
            rating.setCountVote(vote);
//            ratingRepository.save(rating);
        } else {
            rating = new Rating(idRestaurant, 1, date);
        }
//        System.out.println("SET_BY_VOTE rating : "+rating);
        rating.setRestaurant(restaurantRepository.get(idRestaurant));
        ratingRepository.save(rating);
//        System.out.println("SET_BY_VOTE SAVE : "+rating);
    }

    public List<Rating> getAllRatingByRestaurant(int idRest) {
        return ratingRepository.getAllByRestaurant(idRest);
    }

//  Подсчет голосов у ресторана
    public int getRatingByRestaurant(Integer idRestaurant) {
//        List<Rating> all = ratingRepository.getAll();
        List<Rating> all = ratingRepository.getAllByRestaurant(idRestaurant);
        System.out.println("ALL: "+all);
        int vote = 0;
//        int voteAll = 0;
        for (Rating rat : all) {
//            voteAll = voteAll + rat.getCountVote();
//            if (rat.getIdRestaurant() == idRestaurant){
//            if (rat.getRestaurant().getId() == idRestaurant){
                vote = vote + rat.getCountVote();
//            }
        }
        return vote;
    }

//  удаление голоса добавленного в этот день
    public void incrementVote(Integer idRest){
//        Rating rating = getByIdRestDataNow(idRest);
        Rating rating = ratingRepository.getByRestaurantByDate(idRest, LocalDate.now());
        System.out.println("RATING INCREMENT : "+rating);
        if (rating != null) {
            int vote = rating.getCountVote() - 1;
            rating.setCountVote(vote);
            ratingRepository.save(rating);
            System.out.println("INCREMENT SAVE : "+rating);
        }
    }

    private Rating getByIdRestDataNow(int idRest) {
//        List<Rating> all = ratingRepository.getAll();
        List<Rating> all = ratingRepository.getAllByRestaurant(idRest);
        for (Rating r : all) {
//            if (r.getIdRestaurant() == idRest && r.getDateVote().equals(LocalDate.now())) {
//            if (r.getRestaurant().getId() == idRest && r.getDateVote().equals(LocalDate.now())) {
            if (r.getDateVote().equals(LocalDate.now())) {
                return r;
            }
        }
        return null;
    }

    public List<Rating>  getAllByDate(LocalDate date) {
        Assert.notNull(date, "Restaurant must not be NULL");
        List<Rating> ratingList = ratingRepository.getAll();
        return ratingList.stream()
                .filter(r -> r.getDateVote().equals(date))
                .collect(Collectors.toList());
    }

    public Rating getByRestaurantByDate(int idRest, LocalDate date) {
        return ratingRepository.getByRestaurantByDate(idRest, date);
    }

    public Rating getByRestaurantName(String name) {
        return ratingRepository.getByRestaurantName(name);
    }
}
