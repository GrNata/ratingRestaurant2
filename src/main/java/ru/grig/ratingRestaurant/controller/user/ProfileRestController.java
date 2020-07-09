package ru.grig.ratingRestaurant.controller.user;

import org.springframework.stereotype.Controller;
import ru.grig.ratingRestaurant.model.User;

import java.util.List;

import static ru.grig.ratingRestaurant.controller.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }

    public List<User> getAll() {    return super.getAll();  }
}
