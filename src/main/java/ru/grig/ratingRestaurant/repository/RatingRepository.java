package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Rating;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface RatingRepository {

    Rating save(Rating rating);

    Rating get(long id);

    boolean delete(long id);

    List<Rating> getAll();

    List<Rating> getAllByDate(LocalDate date);

//    void setByVote(Long idRestaurant, LocalDate date);
//
//    int getRatingByRestaurant(Long idRestaurant);
}
