package ru.grig.ratingRestaurant.controller.web;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.service.MenuService;
import ru.grig.ratingRestaurant.service.RestaurantService;
import ru.grig.ratingRestaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Controller
//@RequestMapping("/modal")
public class JspModalController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public String modal(Model model, HttpServletRequest request) {
//        model.addAttribute("users", userService.getAll());
        model.addAttribute("rest", restaurantService.getAll());
        String idRest = request.getParameter("rest");
        if (idRest != null) {
            model.addAttribute("menus", menuService.getAllByRestaurant(Integer.parseInt(idRest)));
        }
        System.out.println("MODAL CONTROLLER");
        System.out.println("RES_ID # = "+request.getParameter("rest"));
        return "modal";
    }

    @PostMapping
    public String getModal(HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("MODEL POST CONTROLLER");
        String aa = request.getParameter("a");
        String bb = request.getParameter("b");
        System.out.println("a = "+aa+"   b = "+bb);
        if (aa != null && bb != null) {
            int a = Integer.parseInt(aa);
            int b = Integer.parseInt(bb);
            int echo = a + b;
            model.addAttribute("suma1", String.valueOf(echo));
            model.addAttribute("suma", String.valueOf(echo));
            try {
                System.out.println("TRY");
                response.getWriter().write(String.valueOf(echo));
                System.out.println("echo = "+echo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int restId = Integer.parseInt(request.getParameter("rest"));
        System.out.println("RES_ID POST = "+restId);
        model.addAttribute("menus", menuService.getAllByRestaurant(restId));
        return "modal";

    }

    @GetMapping("/dialog")
    public String getLodin(Model model) {
        System.out.println("LOGIN");
        return "#dialog";
    }

    @GetMapping("/#dialog")
    public String getRegister(Model model) {
        System.out.println("REGISTER");
        return "#dialog";
    }
}
