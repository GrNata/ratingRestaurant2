package ru.grig.ratingRestaurant;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Role;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {

    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "role");

    public static final int NOT_FOUNR_ID = 10;
    public static final int USER_ID = 100000;
    public static final String USER_EMAIL = "dima@gmail.com";

    public static final User USER_1 = new User(USER_ID, "Nata", "nata@gmail.com", "11111",  Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER_2 = new User(USER_ID+1, "Dima", "dima@gmail.com", "22222",  Role.ROLE_USER);
    public static final User USER_3 = new User(USER_ID+2, "Seva", "seva@gmail.com", "33333", Role.ROLE_USER);

    public static User getNew() {
        return new User(null, "NEW NAME", "NEW@bk.ru", "&&&77", new Date(), EnumSet.of(Role.ROLE_ADMIN));
    }

    public static User getUpdate() {
        User updated = new User(USER_ID, "Nata", "nata@gmail.com", "11111",  Role.ROLE_ADMIN, Role.ROLE_USER);
        updated.setName("UPDATE NAME");
//        updated.setEmail("UPDATE@gmail.com");
//        updated.setPassword("7777");
//        updated.setEnable(false);
//        updated.setRole(EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER));
        return updated;
    }

}
