package ru.grig.ratingRestaurant.controller.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.service.RestaurantService;

import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.assureIdConsistent;
import static ru.grig.ratingRestaurant.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    @Autowired
    private RestaurantService restaurantService;


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
