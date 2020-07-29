package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Repository
public class DatajpaRestaurantRepository implements RestaurantRepository {

    private CrudRestaurantRepository crudRestaurantRepository;

    public DatajpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

    @Override
    public void setRatingByRestaurant(Map<Integer, Integer> map, LocalTime time) {

    }
}
