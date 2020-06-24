package ru.grig.ratingRestaurant.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.grig.ratingRestaurant.model.User;

import java.util.List;

@Controller
public class AdminRestController extends AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(AdminRestController.class);

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(long id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        log.info("create user: {}", user);
        return super.create(user);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public void update(User user, long id) {
        super.update(user, id);
    }
}
