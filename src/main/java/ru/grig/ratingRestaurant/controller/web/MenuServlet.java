package ru.grig.ratingRestaurant.controller.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.grig.ratingRestaurant.controller.menu.MenuRestController;
import ru.grig.ratingRestaurant.controller.restaurant.RestaurantRestController;
//import ru.grig.ratingRestaurant.inMemory.InMemoryMenuRepository;
//import ru.grig.ratingRestaurant.inMemory.InMemoryRestaurantRepository;
import ru.grig.ratingRestaurant.util.MenuUtil;

import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuServlet extends HttpServlet {
    private static final Logger log = getLogger(MenuServlet.class);
    private final String MENU = "/menus.jsp";
    private String forward;

    private ConfigurableApplicationContext springContext;
    private MenuRestController menuController;
    private RestaurantRestController restaurantController;
//    @Autowired
//    MenuRepository menuRepository;
////    @Autowired
//    RestaurantRepository restaurantRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        menuController = springContext.getBean(MenuRestController.class);
        restaurantController = springContext.getBean(RestaurantRestController.class);
        //        menuRepository = new InMemoryMenuRepository();
//        restaurantRepository = new InMemoryRestaurantRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("redirect to menus");
        forward = MENU;
//        request.setAttribute("menus", menuRepository.getAll());

//        List<Restaurant> restaurants = restaurantRepository.getAll();
//        List<Menu> menuList = menuRepository.getAll();
//        System.out.println("MenuUtil");
//        List<MenuWithRestaurant> menuWithRestaurants = MenuUtil.getAll(restaurants, menuList);
//        System.out.println("222");
//        System.out.println(menuWithRestaurants);
//        for(MenuWithRestaurant m : menuWithRestaurants){
//            System.out.println(m);
//        }

//        request.setAttribute("menus", MenuUtil.getAll(restaurantRepository.getAll(), menuRepository.getAll()));
        request.setAttribute("menus", MenuUtil.getAll(restaurantController.getAll(), menuController.getAll()));
        request.getRequestDispatcher(forward).forward(request, response);
    }
}
