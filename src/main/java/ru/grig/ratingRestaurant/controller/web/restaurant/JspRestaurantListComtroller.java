package ru.grig.ratingRestaurant.controller.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.service.MenuService;
import ru.grig.ratingRestaurant.service.RatingService;
import ru.grig.ratingRestaurant.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ru.grig.ratingRestaurant.util.ValidationUtil.assureIdConsistent;
import static ru.grig.ratingRestaurant.util.RestaurantUtil.*;

@Controller
@RequestMapping("/restaurantList")
public class JspRestaurantListComtroller extends AbstractRestaurantController{

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/restaurantList";
    }

    @GetMapping("/create")
    public String getRestaurantFormCreate(Model model) {
        Restaurant restaurant = new Restaurant("", 0, 0);
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @GetMapping("/update")
    public String getRestaurantFormUpdate(@RequestParam String id, Model model) {
        int restId = Integer.parseInt(id);
        System.out.println("REST_ID = "+restId);
        Restaurant restaurant = restaurantService.get(restId);
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @PostMapping
    public String createOrUpdate(HttpServletRequest request, Model model) {

        if (request.getParameter("id").isEmpty()) {
            Restaurant restaurant = new Restaurant(request.getParameter("name"),
                    Integer.parseInt(request.getParameter("menu")));
            Restaurant restaurant1 = restaurantService.create(restaurant);
            ratingService.create(new Rating(restaurant1, 0, LocalDate.now()));

        } else {
            Restaurant restaurant = new Restaurant(request.getParameter("name"),
                    Integer.parseInt(request.getParameter("menu")),
                Integer.valueOf(request.getParameter("rating")));

            int restId = Integer.valueOf(request.getParameter("id"));
            assureIdConsistent(restaurant, restId);
            restaurantService.update(restaurant);
            System.out.println("UPDATE RESTAURANT : "+restaurant);
        }
        return "redirect:/restaurantList";
    }

    @GetMapping("/menuList")
    public String getMenuList(@RequestParam String id, Model model) {
        System.out.println("GET restId = "+id);
//        System.out.println("GET 2 "+request.getParameter("id"));
        int restId = Integer.parseInt(id);
        List<Menu> menus = menuService.getAllByRestaurant(restId);
        System.out.println("MENUS: "+menus);
        setIdRestaurant(restId);
        model.addAttribute("menus", menus);

        return "menuList";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
