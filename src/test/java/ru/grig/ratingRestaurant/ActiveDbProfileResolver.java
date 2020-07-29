package ru.grig.ratingRestaurant;

import org.springframework.test.context.ActiveProfilesResolver;

public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        String[] strings = new String[]{Profiles.getActiveDbProfile()};
        System.out.println("STRINGS PROFILES: "+strings);
//        return new String[]{Profiles.getActiveDbProfile()};
        return strings;
    }
}