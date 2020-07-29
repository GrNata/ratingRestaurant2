package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Rating;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRatingRepository extends JpaRepository<Rating, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Rating r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Rating r WHERE r.restaurant.id=:idRestaurant")
    List<Rating> findAllByRestaurant(@Param("idRestaurant") int idRestaurant);

    @Query("SELECT r FROM Rating r WHERE r.restaurant.id=:idRestaurant AND " +
            "r.dateVote=:date")
    Rating findAllByRestaurantAndDateVote(@Param("idRestaurant") int idRestaurant, @Param("date") LocalDate date);

    @Query("SELECT  r FROM Rating r WHERE r.restaurant.name=:name")
    Rating findByRestaurantName(@Param("name") String name);
}
