package ru.grig.ratingRestaurant;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Role;
import ru.grig.ratingRestaurant.model.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int START_USER_SEQ = 100000;
    public static final long NOT_FOUNR_ID = 10;
    public static final long USER_ID = START_USER_SEQ;

    public static final User USER_1 = new User(USER_ID, "Nata", "nata@gmail.com", "1111", LocalDate.of(2020, 02, 10), true, EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER));
    public static final User USER_2 = new User(USER_ID, "Dima", "dima@gmail.com", "2222", LocalDate.of(2020, 04, 15), true,  Role.ROLE_USER);
    public static final User USER_3 = new User(USER_ID, "Seva", "seva@gmail.com", "3333", LocalDate.of(2020, 03, 12), true, Role.ROLE_USER);

    public static User getNew() {
        return new User(null, "NEW NAME", "NEW@bk.ru", "&&&", LocalDate.now(), true, Role.ROLE_USER);
    }

    public static User getUpdate() {
        User updated = new User(USER_1);
        updated.setName("UPDATE NAME");
        updated.setEmail("UPDATE@gmail.com");
        updated.setPassword("7777");
        updated.setEnable(false);
        updated.setRole(EnumSet.of(Role.ROLE_ADMIN));
        return updated;
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "role");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "role").isEqualTo(expected);
    }
}
