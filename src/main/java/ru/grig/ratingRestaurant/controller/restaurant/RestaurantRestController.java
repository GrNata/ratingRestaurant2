package ru.grig.ratingRestaurant.controller.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;
import ru.grig.ratingRestaurant.service.RestaurantService;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;
import java.util.List;

@Controller
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    @Autowired
    private final RestaurantService restaurantService;

    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public Restaurant get(int id) {
        log.info("get id {}", id);
        return restaurantService.get(id);
    }

    public void delete(int id) {
        log.info("delete id {}", id);
        restaurantService.delete(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant: {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant) {
        log.info("update restaurant: ", restaurant);
        assureIdConsistent(restaurant, restaurant.getId());
        restaurantService.update(restaurant);
    }
}
