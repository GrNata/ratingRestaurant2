package ru.grig.ratingRestaurant.controller.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.grig.ratingRestaurant.controller.menu.MenuRestController;
import ru.grig.ratingRestaurant.controller.rating.RatingRestController;
import ru.grig.ratingRestaurant.controller.restaurant.RestaurantRestController;
import ru.grig.ratingRestaurant.controller.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private final String FORM = "/formUser.jsp";
    private final String USERS = "/users.jsp";
    private final String RESTAURANTS = "/restaurants.jsp";

//    UserRepository userRepository;
    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restaurantController;
    private ProfileRestController profileController;
    private RatingRestController ratingController;
    private MenuRestController menuController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext(
                "spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-cache.xml");
        restaurantController = springContext.getBean(RestaurantRestController.class);
        profileController = springContext.getBean(ProfileRestController.class);
        ratingController = springContext.getBean(RatingRestController.class);
        menuController = springContext.getBean(MenuRestController.class);
//        profileController = new InMemoryUserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("redirect to users");
        String forward = "";
//        String action = req.getParameter("action");

        forward = USERS;
//        request.setAttribute("users", userRepository.getAll());
        request.setAttribute("users", profileController.getAll());
        request.getRequestDispatcher(USERS).forward(request, response);
//        response.sendRedirect("users.jsp");
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("POST");
//        String userID = request.getParameter("userId");
//        System.out.println("userID = "+userID);
//        SecurityUtil.setAuthUserId(Long.parseLong(userID));
//        request.setAttribute("rest", RestaurantUtil.getRestaurantByRating(restaurantController.getAll(), ratingController.getAll()));
//        request.getRequestDispatcher(RESTAURANTS).forward(request, response);
//    }
}
