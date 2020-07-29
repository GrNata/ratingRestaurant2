package ru.grig.ratingRestaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grig.ratingRestaurant.service.RatingService;
import ru.grig.ratingRestaurant.service.RestaurantService;
import ru.grig.ratingRestaurant.service.UserService;
import ru.grig.ratingRestaurant.util.RestaurantUtil;
import ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "restaurants";
    }

    @GetMapping("/restaurants")
    public String getMeals(Model model) {
        model.addAttribute("rest",
                RestaurantUtil.getRestaurantByRating(restaurantService.getAll(), ratingService.getAll()));
//                MealsUtil.getTos(mealService.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay()));
        return "restaurants";
    }
}
