package ru.grig.ratingRestaurant;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.grig.ratingRestaurant.controller.menu.MenuRestController;
import ru.grig.ratingRestaurant.controller.restaurant.RestaurantRestController;
import ru.grig.ratingRestaurant.controller.user.AdminRestController;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Role;
import ru.grig.ratingRestaurant.model.User;

import java.time.LocalDate;
import java.util.Arrays;

public class MainSpring {

    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCxt = new ClassPathXmlApplicationContext("spring/spring-app.xml")){
            System.out.println("BEAN definition name: " + Arrays.toString(appCxt.getBeanDefinitionNames()));
            AdminRestController adminRestController = appCxt.getBean(AdminRestController.class);
            adminRestController.create(new User(null, "Roma", "roma@mail.ru", "4444", LocalDate.now(), false, Role.ROLE_USER));

            RestaurantRestController restController = appCxt.getBean(RestaurantRestController.class);
            System.out.println(restController.get(3));

            MenuRestController menuRestController = appCxt.getBean(MenuRestController.class);
            System.out.println(menuRestController.get(2));
            menuRestController.update(new Menu((long) 2, 3, "333-DISH", 100));
            System.out.println(menuRestController.get(2));
        }
    }
}
