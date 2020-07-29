package ru.grig.ratingRestaurant.controller.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.grig.ratingRestaurant.service.RatingService;
import ru.grig.ratingRestaurant.service.RestaurantService;
import ru.grig.ratingRestaurant.util.RestaurantUtil;

//@Controller
//@RequestMapping("/menus")
public class JspMenuController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RatingService ratingService;

    @GetMapping("/restaurants")
    public String getRestaurants(Model model) {
        System.out.println("RESTAURANTS: "+restaurantService.getAll());
//        model.addAttribute("rest", restaurantService.getAll());
        model.addAttribute("rest",
                RestaurantUtil.getRestaurantByRating(restaurantService.getAll(), ratingService.getAll()));
        return "restaurants";
    }

}
