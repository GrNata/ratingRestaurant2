package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Repository
public class DatajpaRestaurantRepository implements RestaurantRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository crudRepository;

    public DatajpaRestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
//    public List<Restaurant> getAll() {
//        return crudRepository.findAll(SORT_NAME);
//    }
    public List<Restaurant> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public void setRatingByRestaurant(Map<Integer, Integer> map, LocalTime time) {
//        не используется
    }
}
