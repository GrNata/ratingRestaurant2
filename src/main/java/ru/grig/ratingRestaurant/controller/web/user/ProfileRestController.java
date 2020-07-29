package ru.grig.ratingRestaurant.controller.web.user;

//import com.sun.org.apache.xerces.internal.impl.dv.xs.ListDV;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.grig.ratingRestaurant.controller.web.SecurityUtil;
import ru.grig.ratingRestaurant.model.User;

import java.util.List;

import static ru.grig.ratingRestaurant.controller.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        System.out.println("PROFILE_REST_CONTROLLER id: "+authUserId());
        return super.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        super.delete(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(User user) {
        super.update(user, authUserId());
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "русский текст";
    }

//    public List<User> getAll() {    return super.getAll();  }
}
