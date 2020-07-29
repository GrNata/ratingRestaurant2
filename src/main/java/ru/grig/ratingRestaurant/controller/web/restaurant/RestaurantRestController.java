package ru.grig.ratingRestaurant.controller.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.service.RestaurantService;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {

    static final String REST_URL_RESTAURANT = "/rest/profile/restaurants";

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

//    @Autowired
//    private final RestaurantService restaurantService;
//
//    public RestaurantRestController(RestaurantService restaurantService) {
//        this.restaurantService = restaurantService;
//    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get id {}", id);
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete id {}", id);
        super.delete(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return super.getAll();
    }

//    public Restaurant create(Restaurant restaurant) {
//        log.info("create restaurant: {}", restaurant);
//        checkNew(restaurant);
//        return restaurantService.create(restaurant);
//    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        log.info("update restaurant: ", restaurant);
//        assureIdConsistent(restaurant, restaurant.getId());
        super.update(restaurant);
    }
}
