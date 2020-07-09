package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Rating;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRatingRepository extends JpaRepository<Rating, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Rating r WHERE r.id=:id")
    public int delete(@Param("id") int id);

    @Query("SELECT r FROM Rating r WHERE r.restaurant.id=:restId")
    public List<Rating> getAllByRestaurant(@Param("restId") int restId);

    @Query("SELECT r FROM Rating r WHERE r.restaurant.id=:restId AND r.dateVote=:date")
    public Rating getByRestaurantByDate(@Param("restId") int restId, @Param("date") LocalDateTime date);

    @Query("SELECT r FROM Rating r WHERE r.dateVote=:date")
    public List<Rating> getAllByDate(@Param("date") LocalDateTime date);

    @Query("SELECT r FROM Rating r WHERE r.id=:id AND r.restaurant.id=:restId")
    public Rating getByRestaurant(@Param("id") int id, @Param("restId") int restId);
}
