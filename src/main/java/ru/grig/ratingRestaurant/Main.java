package ru.grig.ratingRestaurant;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.repository.inMemory.InMemoryMenuRepository;
import ru.grig.ratingRestaurant.repository.inMemory.InMemoryRatingRepository;
import ru.grig.ratingRestaurant.repository.inMemory.InMemoryRestaurantRepository;
import ru.grig.ratingRestaurant.repository.inMemory.InMemoryUserRepository;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

        InMemoryUserRepository memoryUserRepository = new InMemoryUserRepository();
        InMemoryRestaurantRepository memoryRestaurantRepository = new InMemoryRestaurantRepository();
        InMemoryMenuRepository menuRepository = new InMemoryMenuRepository();
        InMemoryRatingRepository ratingRepositpry = new InMemoryRatingRepository();

//        for (User u : memoryUserRepository.getAll())
//            System.out.println(u);
//        System.out.println("______________________");
//        for (Restaurant r : memoryRestaurantRepository.getAll())
//            System.out.println(r);
//        System.out.println("______________________");
//        for (Menu m : menuRepository.getAll())
//            System.out.println(m);
//        System.out.println("______________________");
//        LocalDate date = LocalDate.of(2020, Month.MAY, 10);
//        for (Rating rat : ratingRepositpry.getAllByDate(date))
//            System.out.println(rat);
//        System.out.println("______________________");
//        LocalDate dateNow = LocalDate.now();
//        ratingRepositpry.save(new Rating(3, 1, dateNow));
//        for (Rating rat : ratingRepositpry.getAllByDate(dateNow))
//            System.out.println(rat);
//        System.out.println("__________________________");
        LocalDate date2 = LocalDate.of(2020, Month.MAY, 10);
        long idRest = 2;
//        ratingRepositpry.setByVote(idRest, date2);
//        for (Rating rat : ratingRepositpry.getAllByDate(date2))
//            System.out.println(rat);
    }
}
