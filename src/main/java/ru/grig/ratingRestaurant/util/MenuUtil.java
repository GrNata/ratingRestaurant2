package ru.grig.ratingRestaurant.util;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.with_restaurant.MenuWithRestaurant;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

    public static List<MenuWithRestaurant> getAll(List<Restaurant> restaurants, List<Menu> menus) {
        List<MenuWithRestaurant> menuWithRestaurants = new ArrayList<>();
        for (Menu m : menus){
            String nameRestaurant = "";
            for (Restaurant r : restaurants) {
                if (r.getId() == m.getIdRestaurant()) {
                    nameRestaurant = r.getName();
                    break;
                }
            }
            menuWithRestaurants.add(new MenuWithRestaurant(
                    m.getId(), m.getIdRestaurant(), m.getDishes(), m.getPrice(),
                    nameRestaurant));
        }
        return menuWithRestaurants;
    }
}
