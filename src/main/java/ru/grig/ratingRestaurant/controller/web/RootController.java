package ru.grig.ratingRestaurant.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.service.MenuService;
import ru.grig.ratingRestaurant.service.RatingService;
import ru.grig.ratingRestaurant.service.RestaurantService;
import ru.grig.ratingRestaurant.service.UserService;
import ru.grig.ratingRestaurant.util.RestaurantUtil;
import ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.assureIdConsistent;

@Controller
public class RootController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public String root() {  return "index"; }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        if (request.getParameter("userId") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            SecurityUtil.setAuthUserId(userId);
        }
        return "redirect:restaurants";
    }

    @GetMapping("/restaurants")
    public String getRestaurants(Model model) {
        System.out.println("RESTAURANTS: "+restaurantService.getAll());
//        model.addAttribute("rest", restaurantService.getAll());
        List<RestaurantWithRating> restaurantWithRating = RestaurantUtil.getRestaurantByRating(restaurantService.getAll(), ratingService.getAll());
        model.addAttribute("rest", restaurantWithRating);
//        model.addAttribute("menus", menuService.getAll());
//        for (RestaurantWithRating r : restaurantWithRating) {
//            model.addAttribute("menus", menuService.getAllByRestaurant(r.getId()));
//        }
        return "restaurants";
    }

    @PostMapping("/restaurants")
    public String setRestaurant(HttpServletRequest request) {
        if (request.getParameter("userId") != null) {
            int restId = Integer.parseInt(request.getParameter("restId"));
            SecurityUtil.setAuthUserId(restId);
        }
        return "redirect:restaurants";
    }


    @GetMapping("/restaurantList")
    public String getRestaurantForm(Model model) {
        List<RestaurantWithRating> restaurantWithRating = RestaurantUtil.getRestaurantByRating(restaurantService.getAll(), ratingService.getAll());
        List<Menu> menus = menuService.getAll();
        System.out.println("!!!! MENUS: "+menus);
        model.addAttribute("menus", menus);
        model.addAttribute("rest", restaurantWithRating);
        return "restaurantList";
    }

//    @GetMapping("/restaurants/menus")
//    public String modal(Model model) {
//        return "menus";
//    }

//    @PostMapping
//    public String modal(HttpServletRequest request, Model model) {
//        String restId = request.getParameter("id");
//        System.out.println("!!!! GET REST_ID = "+restId);
////        List<Menu> menus = menuService.getAllByRestaurant(Integer.parseInt(id));
//        List<Menu> menus = menuService.getAll();
//        System.out.println("!!!! MENUS: "+menus);
//        model.addAttribute("menus", menus);
//        return "restaurants";
//    }
}
