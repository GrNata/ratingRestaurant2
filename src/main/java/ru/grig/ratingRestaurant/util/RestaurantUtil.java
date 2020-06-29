package ru.grig.ratingRestaurant.util;

import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static List<RestaurantWithRating> getRestaurantByRating(List<Restaurant> restaurants, List<Rating> ratings) {
        List<RestaurantWithRating> restaurantWithRatings = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();   //  Long - restaurant id, Integer -sum of vote
        for (Rating rat : ratings) {
//            if (map.get(rat.getIdRestaurant()) == null) {
            if (map.get(rat.getRestaurant().getId()) == null) {
//                map.put(rat.getIdRestaurant(), rat.getCountVote());
                map.put(rat.getRestaurant().getId(), rat.getCountVote());
            }
//            if (map.get(rat.getIdRestaurant()) != null) {
            if (map.get(rat.getRestaurant().getId()) != null) {
//                int vote = map.get(rat.getIdRestaurant()) + rat.getCountVote();
                int vote = map.get(rat.getRestaurant().getId()) + rat.getCountVote();
//                map.put(rat.getIdRestaurant(), vote);
                map.put(rat.getRestaurant().getId(), vote);

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
