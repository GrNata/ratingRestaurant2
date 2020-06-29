package ru.grig.ratingRestaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.getALL).getResultList();
    }

    @Override
    public void setRatingByRestaurant(Map<Integer, Integer> map, LocalTime time) {

    }
}
