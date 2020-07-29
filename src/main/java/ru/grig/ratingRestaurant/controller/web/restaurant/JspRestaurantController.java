package ru.grig.ratingRestaurant.controller.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.service.MenuService;
import ru.grig.ratingRestaurant.service.RatingService;
import ru.grig.ratingRestaurant.service.RestaurantService;
import ru.grig.ratingRestaurant.service.VoteService;
import ru.grig.ratingRestaurant.util.RestaurantUtil;
import ru.grig.ratingRestaurant.util.VoteUtil;
import ru.grig.ratingRestaurant.with_restaurant.VoteWithRestaurant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static ru.grig.ratingRestaurant.controller.web.SecurityUtil.authUserId;
import static ru.grig.ratingRestaurant.controller.web.SecurityUtil.getTimeBefore;
import static ru.grig.ratingRestaurant.util.ValidationUtil.assureIdConsistent;

@Controller
@RequestMapping("/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    MenuService menuService;
    @Autowired
    RatingService ratingService;
    @Autowired
    VoteService voteService;

    @GetMapping("/menus")
//    @GetMapping("/zatemnenie")
    public String getMenus(@RequestParam String id,  Model model) {
        System.out.println("GET "+id);
//        System.out.println("GET 2 "+request.getParameter("id"));
        List<Menu> menus = menuService.getAllByRestaurant(Integer.parseInt(id));
        System.out.println("MENUS: "+menus);
        model.addAttribute("menus", menus);
        return "/menus";
//        return "restaurants";
    }

    @GetMapping("/historyVote")
    public String getHistoryVote(Model model) {
        List<Vote> votes = voteService.getAllByUser(authUserId());
        List<VoteWithRestaurant> voteList = VoteUtil.getVoteWithRestaurant(votes, restaurantService);
        model.addAttribute("votes", voteList);
        return "/historyVote";
    }

    @PostMapping
    public String getVote(HttpServletRequest request, Model model) {
        System.out.println("POST ");
        String id_rest = request.getParameter("vote");
        System.out.println("POST vote (restId) "+request.getParameter("vote"));
//        String butOk = request.getParameter("butOk");
//        System.out.println("POST butOk "+butOk);
        if (id_rest == null) {
            model.addAttribute("rest", RestaurantUtil.getRestaurantByRating(restaurantService.getAll(), ratingService.getAll()));
//            return "restaurants";
            return "redirect:/restaurants";
        } else {
            int restId = Integer.parseInt(id_rest);
            doIncrementVote(restId);
            model.addAttribute("rest", restaurantService.get(restId));
            model.addAttribute("menus", menuService.getAllByRestaurant(restId));
//            return "voteRestaurant";
            return "redirect:/restaurants";
        }
    }


    @GetMapping("/voteRestaurant")
    public String getRestaurants(Model model) {
        System.out.println("RESTAURANTS: "+restaurantService.getAll());
//        model.addAttribute("rest", restaurantService.getAll());
        model.addAttribute("rest", RestaurantUtil.getRestaurantByRating(restaurantService.getAll(), ratingService.getAll()));
        return "restaurants";
    }

    private void doIncrementVote(int restId) {
        // проверка времени голосования
        if (LocalTime.now().isBefore(getTimeBefore())) {

            System.out.println("POST INCREMENT  voteId="+restId+"  getUserId="+authUserId());
            Vote vote = new Vote(authUserId());
            System.out.println("VOTE 1 : "+vote);
            vote.setIdRestaurant(restId);

            System.out.println("VOTE 2 : "+vote);

            Integer IdRestBefore = voteService.update(vote, authUserId());
            System.out.println("INCREM ID_REST_BEFORE = "+IdRestBefore);
            // проверка было голосование сегодня
            if (IdRestBefore != null) {
                System.out.println("INCREMENT RATING DATE: "+ratingService.getByRestaurantByDate(restId, LocalDate.now()));
                ratingService.incrementVote(IdRestBefore);

            }

            ratingService.setByVote(restId, LocalDate.now());
            System.out.println("RATING : "+ratingService.getAllByRestaurant(restId));
            System.out.println("RATING DATE : "+ratingService.getByRestaurantByDate(restId, LocalDate.now()));
        } else {
            System.out.println("Сегодня голосование окончено!");
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

//    @GetMapping("/modal")
//    public String modal(Model model) {
////        model.addAttribute("users", userService.getAll());
//        model.addAttribute("rest", restaurantService.getAll());
//        model.addAttribute("menus", menuService.getAll());
//        System.out.println("MODAL CONTROLLER 2");
//        return "modal";
//    }


}
