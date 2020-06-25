package ru.grig.ratingRestaurant;


import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Rating;

import java.util.Arrays;

import static ru.grig.ratingRestaurant.RestaurantTestData.REST_ID_1;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator();

    public static final long NOT_FOUNR_ID = 10;
    public static final long MENU_ID = 100000;
    public static final long MENU_ID_REST = REST_ID_1;

    public static final Menu MENU_1_1 = new Menu(MENU_ID, REST_ID_1, "dish 1", 100);
    public static final Menu MENU_1_2 = new Menu(MENU_ID+1, REST_ID_1,  "dish 2", 150);
    public static final Menu MENU_1_3 = new Menu(MENU_ID+2, REST_ID_1, "dish 3", 130);
    public static final Menu MENU_2_1 = new Menu(MENU_ID+3, REST_ID_1+1, "2dish 1", 200);
    public static final Menu MENU_2_2 = new Menu(MENU_ID+4, REST_ID_1+1, "2dish 2", 250);
    public static final Menu MENU_2_3 = new Menu(MENU_ID+5, REST_ID_1+1, "2dish 3", 230);
    public static final Menu MENU_3_1 = new Menu(MENU_ID+6, REST_ID_1+2, "3dish 1", 300);
    public static final Menu MENU_3_2 = new Menu(MENU_ID+7, REST_ID_1+2, "3dish 2", 350);
    public static final Menu MENU_3_3 = new Menu(MENU_ID+8, REST_ID_1+2, "3dish 3", 320);

    public static Menu getNew() {
        return new Menu(null, REST_ID_1, "NEW DISH", 555);
    }

    public static Menu getUpdated() {
        Menu updated = new Menu(MENU_1_1);
        updated.setDishes("UPDATE DISH");
        updated.setPrice(777);
        return updated;
    }

}
