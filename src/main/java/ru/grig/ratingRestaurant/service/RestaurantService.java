package ru.grig.ratingRestaurant.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;
import org.springframework.util.Assert;

import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant){
        Assert.notNull(restaurant, "Restaurant must not be NULL");
        return  restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be NULL");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    public int updateCountMenu(Restaurant restaurant, int count) {
        int countMenu = restaurant.getMenu() + count;
        restaurant.setMenu(countMenu);
        update(restaurant);
        return countMenu;
    }

//    public List<Restaurant> getAllByCountMenu() {
//        List<Restaurant> restaurants = getAll();
//        for (Restaurant r : restaurants) {
//            List<Menu> menus = menuRepository
//        }
//        return null;
//    }
}
