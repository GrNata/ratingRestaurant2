package ru.grig.ratingRestaurant.controller.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.grig.ratingRestaurant.controller.SecurityUtil;
import ru.grig.ratingRestaurant.controller.menu.MenuRestController;
import ru.grig.ratingRestaurant.controller.rating.RatingRestController;
import ru.grig.ratingRestaurant.controller.restaurant.RestaurantRestController;
import ru.grig.ratingRestaurant.controller.vote.VoteRestController;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.service.UserService;
import ru.grig.ratingRestaurant.util.MenuUtil;
import ru.grig.ratingRestaurant.util.RestaurantUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

import static ru.grig.ratingRestaurant.controller.SecurityUtil.*;

public class RestaurantServlet extends HttpServlet {
    private static final Logger log = getLogger(RestaurantServlet.class);

    private final LocalTime TIME = LocalTime.of(11, 00);

    private final String RESTAURANTS = "/restaurants.jsp";
    private final String FORM = "/formRestaurant.jsp";
    private final String MENU = "/menus.jsp";
    private String action;
    private String forward;

//    private long idVote = 0;

    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restaurantController;
    private MenuRestController menuController;
    private RatingRestController ratingController;
    private VoteRestController voteController;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext(
                "spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-cache.xml");
        System.out.println("SPRING_CONTEXT: "+springContext);
        restaurantController = springContext.getBean(RestaurantRestController.class);
        menuController = springContext.getBean(MenuRestController.class);
        ratingController = springContext.getBean(RatingRestController.class);
        voteController = springContext.getBean(VoteRestController.class);
        userService = springContext.getBean(UserService.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("redirect to restaurants userId = {}", SecurityUtil.authUserId());
        action = request.getParameter("action");
        System.out.println("ACTION = "+action);
        switch ((action == null || action.isEmpty()) ? "list" : action) {
            case "list" :
                forward = RESTAURANTS;
                request.setAttribute("rest", RestaurantUtil.getRestaurantByRating(restaurantController.getAll(), ratingController.getAll()));
                break;
            case "menu" :
                forward = MENU;
                int idRestaurant = Integer.parseInt(request.getParameter("id"));
//                request.setAttribute("menus", menuController.getAllByRestaurant(idRestaurant));
                request.setAttribute("menus", MenuUtil.getAll(restaurantController.getAll(), menuController.getAllByRestaurant(idRestaurant)));
                break;

        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String  action = request.getParameter("action");
        String userID = request.getParameter("userId");
        String  restId = request.getParameter("vote");
        String but1 = request.getParameter("butOK");
        String but2 = request.getParameter("butCancel");

        log.info("POSt action {}, userId {}", action, userID);

        if (restId != null && but1 != null) {
            if (LocalTime.now().isBefore(getTimeBefore())) {
                Integer RestID = Integer.parseInt(restId);
                Vote vote = new Vote(userService.get(authUserId()), restaurantController.get(RestID));
                Integer IdRestBefore = voteController.update(vote, authUserId());

                if (IdRestBefore != null) {
                    ratingController.incrementVote(IdRestBefore);
                }

                ratingController.setByVote(RestID, LocalDate.now().atTime(00, 00));

                request.setAttribute("rest", restaurantController.get(RestID));
                request.setAttribute("menus", menuController.getAllByRestaurant(RestID));
                request.getRequestDispatcher("/voteRestaurant.jsp").forward(request, response);
            } else {
//                request.setAttribute("note", "Голосование окончено!");
                request.setAttribute("rest", RestaurantUtil.getRestaurantByRating(restaurantController.getAll(), ratingController.getAll()));
                request.getRequestDispatcher(RESTAURANTS).forward(request, response);
            }
        }
        if (but2 != null) {
            request.setAttribute("rest", RestaurantUtil.getRestaurantByRating(restaurantController.getAll(), ratingController.getAll()));
            request.getRequestDispatcher(RESTAURANTS).forward(request, response);
        }
        if (userID != null) {
            log.info("POST userId {}", userID);
                SecurityUtil.setAuthUserId(Integer.parseInt(userID));
                //  проблема в RestaurantUtil.getRestaurantByRating
            request.setAttribute("rest", RestaurantUtil.getRestaurantByRating(restaurantController.getAll(), ratingController.getAll()));
            request.getRequestDispatcher(RESTAURANTS).forward(request, response);

        }

    }
}
