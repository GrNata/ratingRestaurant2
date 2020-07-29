package ru.grig.ratingRestaurant;


import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.model.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.grig.ratingRestaurant.RestaurantTestData.*;

public class MenuTestData {

    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator(Menu.class,"restaurant");

    public static final int NOT_FOUNR_ID = 10;
    public static final int MENU_ID = 100006;
    public static final int MENU_ID_REST = REST_ID_1;
//    public static final Restaurant REST1 = new Restaurant(REST_ID_1, "MD", 3, 0);
//    public static final Restaurant REST2 = new Restaurant(REST_ID_1+1, "Burger", 3, 0);
//    public static final Restaurant REST3 = new Restaurant(REST_ID_1+2, "Cafe", 3, 0);

//    public static final List<Menu> MENU_LIST = new ArrayList<>()
//        .add(MENU_1_1);
//    MENU_1_2, MENU_1_3, MENU_2_1, MENU_2_2, MENU_2_3, MENU_3_1, MENU_3_2, MENU_3_3);

    public static final Menu MENU_1_1 = new Menu(MENU_ID, REST_1, "dish 1", 100);
    public static final Menu MENU_1_2 = new Menu(MENU_ID+1, REST_1,  "dish 2", 150);
    public static final Menu MENU_1_3 = new Menu(MENU_ID+2, REST_1, "dish 3", 130);
    public static final Menu MENU_2_1 = new Menu(MENU_ID+3, REST_2, "2dish 1", 200);
    public static final Menu MENU_2_2 = new Menu(MENU_ID+4, REST_2, "2dish 2", 250);
    public static final Menu MENU_2_3 = new Menu(MENU_ID+5, REST_2, "2dish 3", 230);
    public static final Menu MENU_3_1 = new Menu(MENU_ID+6, REST_3, "3dish 1", 300);
    public static final Menu MENU_3_2 = new Menu(MENU_ID+7, REST_3, "3dish 2", 350);
    public static final Menu MENU_3_3 = new Menu(MENU_ID+8, REST_3, "3dish 3", 320);

    public static Menu getNew() {
        return new Menu(null, REST_1, "NEW DISH", 555);
    }

    public static Menu getUpdated() {
        Menu updated = new Menu(MENU_1_1);
//        Menu updated = MENU_1_1;
//        updated.setRestaurant(REST_2);
        updated.setDishes("UPDATE DISH");
        updated.setPrice(777);
        return updated;
    }

    public static List<Menu> MENUS = List.of(MENU_1_1, MENU_1_2, MENU_1_3,
            MENU_2_1, MENU_2_2, MENU_2_3,
            MENU_3_1, MENU_3_2, MENU_3_3);

    public static List<Menu> MENU_REST1 = List.of(MENU_1_1, MENU_1_2, MENU_1_3);

}
