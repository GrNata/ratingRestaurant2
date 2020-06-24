package ru.grig.ratingRestaurant.util;

import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static List<RestaurantWithRating> getRestaurantByRating(List<Restaurant> restaurants, List<Rating> ratings) {
        List<RestaurantWithRating> restaurantWithRatings = new ArrayList<>();
        Map<Long, Integer> map = new HashMap<>();   //  Long - restaurant id, Integer -sum of vote
        for (Rating rat : ratings) {
            if (map.get(rat.getIdRestaurant()) == null) {
                map.put(rat.getIdRestaurant(), rat.getCountVote());
            }
            if (map.get(rat.getIdRestaurant()) != null) {
                int vote = map.get(rat.getIdRestaurant()) + rat.getCountVote();
                map.put(rat.getIdRestaurant(), vote);

            }
        }

        for (Restaurant res : restaurants){
            restaurantWithRatings.add(new RestaurantWithRating(res.getId(), res.getName(), res.getMenu(), map.get(res.getId())));
        }
        return restaurantWithRatings.stream()
//                .sorted(Comparator.comparing(o -> o.getRating()))
                .sorted(Comparator.comparingInt(RestaurantWithRating::getRating).reversed())
                .collect(Collectors.toList());
    }
}
