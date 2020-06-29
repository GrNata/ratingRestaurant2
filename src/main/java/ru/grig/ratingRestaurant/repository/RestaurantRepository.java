package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.model.Restaurant;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    boolean delete(int id);

    List<Restaurant> getAll();

    void setRatingByRestaurant(Map<Integer, Integer> map, LocalTime time);     //  не используется, через доп. список каждый раз подсчет

//    void addRatingByRestaurant(long id);
}
