package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DatajpaRatingRepository implements RatingRepository {

    private CrudRatingRepository crudRatingRepository;

    public DatajpaRatingRepository(CrudRatingRepository ratingRepository) {
        this.crudRatingRepository = ratingRepository;
    }

    @Override
//    @Transactional
    public Rating save(Rating rating) {
//        System.out.println("SAVE Datajpa : "+rating+"  restId = "+rating.getRestaurant().getId());
        return crudRatingRepository.save(rating);
    }

    @Override
    public Rating get(int id) {
        return crudRatingRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudRatingRepository.delete(id) != 0;
    }

    @Override
    public List<Rating> getAll() {
        return crudRatingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByRestaurant(int idRestaurant) {
        return crudRatingRepository.findAllByRestaurant(idRestaurant);
    }

    @Override
    public Rating getByRestaurantByDate(int idRest, LocalDate date) {
        return crudRatingRepository.findAllByRestaurantAndDateVote(idRest, date);
    }

    @Override
    public Rating getByRestaurantName(String name) {
        return crudRatingRepository.findByRestaurantName(name);
    }
}
