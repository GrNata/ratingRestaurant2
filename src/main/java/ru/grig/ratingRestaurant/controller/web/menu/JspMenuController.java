package ru.grig.ratingRestaurant.controller.web.menu;

import org.dom4j.rule.Mode;
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
import ru.grig.ratingRestaurant.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ru.grig.ratingRestaurant.util.RestaurantUtil.*;
import static ru.grig.ratingRestaurant.util.ValidationUtil.assureIdConsistent;

@Controller
@RequestMapping("/menuList")
public class JspMenuController extends AbstractMenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/1")
//    public String getMenuList(@RequestParam String id, Model model) {
    public String getMenuList(Model model) {
        System.out.println("GET !!! restId = "+getIdRestaurant());
//        System.out.println("GET 2 "+request.getParameter("id"));
//        int restId = Integer.parseInt(id);
        int restId = getIdRestaurant();
        List<Menu> menus = menuService.getAllByRestaurant(restId);
        System.out.println("MENUS: "+menus);
        setIdRestaurant(restId);
        model.addAttribute("menus", menus);
        return "menuList";
    }

    @GetMapping("/delete")
    public String deleteMenu(HttpServletRequest request) {
        int restId = getIdRestaurant();

        System.out.println("REST_ID = "+restId);

        super.delete(getId(request), getIdRestaurant());

        Restaurant restaurant = restaurantService.get(getIdRestaurant());
        restaurantService.updateCountMenu(restaurant, -1);

        return "redirect:/menuList/1";
    }

    @GetMapping("/create")
    public String createMenu(Model model) {
        Menu menu = new Menu("", 0);
        model.addAttribute("menu", menu);
        return "menuForm";
    }

    @GetMapping("/update")
    public String updateMenu(@RequestParam String id, Model model) {
        int restId = getIdRestaurant();

        System.out.println("REST_ID = "+restId);

        Menu menu = menuService.get(Integer.parseInt(id), restId);
        model.addAttribute("menu", menu);
        return "menuForm";
    }

    @PostMapping
    public String createOrUpdateMenu(HttpServletRequest request, Model model) {
        Menu menu = new Menu(request.getParameter("dishes"),
                Integer.parseInt(request.getParameter("price")));
        System.out.println("REQUEST ID = "+request.getParameter("id"));
        if (request.getParameter("id").isEmpty()) {
            Menu menu1 = menuService.create(menu, getIdRestaurant());

            Restaurant restaurant = restaurantService.get(getIdRestaurant());
            restaurantService.updateCountMenu(restaurant, 1);

            menu1.setRestaurant(restaurantService.get(getIdRestaurant()));
            System.out.println("CREATE MENU = "+menu1+" - "+menu1.getRestaurant());

        } else {
            int menuId = Integer.valueOf(request.getParameter("id"));
            assureIdConsistent(menu, menuId);
            menuService.update(menu, getIdRestaurant());
            System.out.println("UPDATE MENU : "+menu);
        }
        return "redirect:/menuList/1";
//        return "/menuList";
//        return "redirect:/menuList";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
