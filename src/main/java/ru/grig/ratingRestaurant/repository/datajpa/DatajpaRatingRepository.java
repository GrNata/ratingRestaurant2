package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.MenuRepository;
import ru.grig.ratingRestaurant.repository.RatingRepository;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DatajpaRatingRepository implements RatingRepository {

    private final CrudRatingRepository crudRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DatajpaRatingRepository(CrudRatingRepository crudRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRepository = crudRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Rating save(Rating rating, int restId) {
        if (!rating.isNew() && getByRestaurant(rating.getId(), restId) == null) {
            return null;
        }
        rating.setRestaurant(crudRestaurantRepository.getOne(restId));
        return crudRepository.save(rating);
    }

    public Rating getByRestaurant(int id, int restId) {
        return crudRepository.getByRestaurant(id, restId);
    }

    @Override
    public Rating get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public List<Rating> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<Rating> getAllByRestaurant(int idRestaurant) {
        return crudRepository.getAllByRestaurant(idRestaurant);
    }

    @Override
    public Rating getByRestaurantByDate(int idRest, LocalDateTime date) {
        return crudRepository.getByRestaurantByDate(idRest, date);
    }

    @Override
    public List<Rating> getAllByDate(LocalDateTime dateTime) {
        return crudRepository.getAllByDate(dateTime);
    }
}
