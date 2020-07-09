package ru.grig.ratingRestaurant.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @CacheEvict(value = "rating", allEntries = true)
    public Rating create(Rating rating, int restId) {
        Assert.notNull(rating, "Restaurant must not be NULL");
        return ratingRepository.save(rating, restId);
    }

    public Rating get(int id) {
        return checkNotFoundWithId(ratingRepository.get(id), id);
    }

    @CacheEvict(value = "rating", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(ratingRepository.delete(id), id);
    }

    @Cacheable("rating")
    public List<Rating> getAll() {
        return ratingRepository.getAll();
    }

    @CacheEvict(value = "rating", allEntries = true)
    public void update(Rating rating, int restId) {
        Assert.notNull(rating, "Restaurant must not be NULL");
        checkNotFoundWithId(ratingRepository.save(rating, restId), rating.getId());
    }

    public List<Rating> getAllByRestaurant(int idRest) {
        List<Rating> all = ratingRepository.getAllByRestaurant(idRest);
        return all.size() == 0 ? null : all;
    }

//    public void setByVote(Integer idRestaurant, LocalDate date) {
    public void setByVote(Integer idRestaurant, LocalDateTime date) {
        Rating rating = ratingRepository.getByRestaurantByDate(idRestaurant, date);

        if (rating != null) {
            int vote = rating.getCountVote();
            vote++;
            rating.setCountVote(vote);
//            ratingRepository.save(rating);
        } else {
            rating = new Rating(1, date);
        }
        ratingRepository.save(rating, idRestaurant);
    }

    public List<Rating> getAllRatingByRestaurant(int idRest) {
        return ratingRepository.getAllByRestaurant(idRest);
    }

//  Подсчет голосов у ресторана
    public int getRatingByRestaurant(Integer idRestaurant) {
        int vote = 0;
        List<Rating> all = ratingRepository.getAllByRestaurant(idRestaurant);
        if (all != null) {
            for (Rating rat : all) {
                vote = vote + rat.getCountVote();
            }
        }
        return vote;
    }

//  удаление голоса добавленного в этот день
    public void incrementVote(Integer idRest){
//  при неверной дате отсекам здесь
        Rating rating = ratingRepository.getByRestaurantByDate(idRest, LocalDate.now().atTime(00, 00));
        if (rating != null) {
            int vote = rating.getCountVote() - 1;
            rating.setCountVote(vote);
            ratingRepository.save(rating, idRest);
        }
    }


//    public List<Rating>  getAllByDate(LocalDate date) {
    public List<Rating>  getAllByDate(LocalDateTime date) {
        Assert.notNull(date, "Restaurant must not be NULL");
        List<Rating> ratingList = ratingRepository.getAll();
        return ratingList.stream()
                .filter(r -> r.getDateVote().toLocalDate().equals(date.toLocalDate()))
                .collect(Collectors.toList());
    }

    public Rating getByRestaurantByDate(int idRest, LocalDate date) {
//        return ratingRepository.getByRestaurantByDate(idRest, date.atTime(00, 00));
        return checkNotFound(ratingRepository.getByRestaurantByDate(idRest, date.atTime(00, 00)),
                "Restaurant with this date not found");
    }
}
