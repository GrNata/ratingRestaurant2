package ru.grig.ratingRestaurant.util;

import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.service.RestaurantService;
import ru.grig.ratingRestaurant.service.VoteService;
import ru.grig.ratingRestaurant.with_restaurant.VoteWithRestaurant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoteUtil {

//    private RestaurantService restaurantService;
//
//    public VoteUtil(RestaurantService restaurantService){
//        this.restaurantService = restaurantService;
//    }

    public static List<VoteWithRestaurant> getVoteWithRestaurant(List<Vote> votes, RestaurantService restaurantService) {
        List<VoteWithRestaurant> voteWithRestaurants = new ArrayList<>();
        for (Vote v : votes) {
            voteWithRestaurants.add(
                    new VoteWithRestaurant(restaurantService.get(v.getIdRestaurant()).getName(), v.getVoteDate()));
        }
        return voteWithRestaurants;
    }
}
