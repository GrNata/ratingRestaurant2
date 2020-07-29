package ru.grig.ratingRestaurant.controller.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.service.UserService;
import static ru.grig.ratingRestaurant.util.ValidationUtil.checkNew;
import static ru.grig.ratingRestaurant.util.ValidationUtil.assureIdConsistent;

import java.util.List;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll(){
        log.info("controller getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("controller get user by id=" + id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("controller create user {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("controller delete user id={}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("controller update user: {}", user);
        assureIdConsistent(user, id);
        service.update(user);
    }
}
