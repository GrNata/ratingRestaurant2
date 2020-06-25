package ru.grig.ratingRestaurant.inMemory;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//@Repository
public class InMemoryRestaurantRepository  {
//public class InMemoryRestaurantRepository extends InMemoryBaseRepository<Restaurant> implements RestaurantRepository {
    private Map<Long, Restaurant> repository = new ConcurrentHashMap<>();
//    private AtomicLong counter = new AtomicLong(0);

//    {
//        save(new Restaurant("MD", 3, 0));
//        save(new Restaurant("Burger", 3, 0));
//        save(new Restaurant("Cafe", 3, 0));
//    }

    //
//    @Override
//    public Restaurant save(Restaurant restaurant) {
//        if (restaurant.isNew()) {
//            restaurant.setId(counter.incrementAndGet());
//        }
//        return repository.put(restaurant.getId(), restaurant);
//    }
//
//    @Override
//    public Restaurant get(long id) {
//        return repository.get(id);
//    }
//
//    @Override
//    public void delete(long id) {
//    }

//    @Override
//    public List<Restaurant> getAll() {
//        return getCollection().stream()
//                .sorted(Comparator.comparing(Restaurant::getName))
//                .collect(Collectors.toList());
//    }
//
////    @Override
////    public void addRatingByRestaurant(long id){
////        get(id).setRating(get(id).getRating() + 1);
////    }
//
//    @Override       //  не используется, через доп. список каждый раз подсчет
//    public void setRatingByRestaurant(Map<Long, Integer> rating, LocalTime time) {
////        List<Restaurant> restaurants = getAll();
//        for (Map.Entry<Long, Integer> rat : rating.entrySet()) {
//            repository.get(rat.getKey()).setRating(rat.getValue());
//        }
//    }
}
