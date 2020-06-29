package ru.grig.ratingRestaurant.controller.web;

import java.time.LocalTime;

public class SecurityUtil {
    private static LocalTime TIME_BEFORE = LocalTime.of(21, 00);

    public SecurityUtil() {
    }

    private static int id;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id){
        int id1 = 0;
        if (id == 1)  {
            id1 = 100000;
        } else
            if (id == 2) {
                id1 = 100001;
            } else
            if (id == 3) {
                id1 = 100002;
            } else {
                id1 = id;
            }
//        SecurityUtil.id = id;
        SecurityUtil.id = id1;
    }

    public static LocalTime getTimeBefore(){    return TIME_BEFORE; }

}
