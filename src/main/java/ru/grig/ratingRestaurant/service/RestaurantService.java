package ru.grig.ratingRestaurant.service;

import org.springframework.stereotype.Service;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant){
        return  restaurantRepository.save(restaurant);
    }

    public Restaurant get(long id) {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    public void delete(long id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public void update(Restaurant restaurant) {
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }
}
