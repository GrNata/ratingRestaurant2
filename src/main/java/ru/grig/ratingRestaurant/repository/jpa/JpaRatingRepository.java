package ru.grig.ratingRestaurant.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRatingRepository implements RatingRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Rating save(Rating rating) {
        if (rating.isNew()) {
            em.persist(rating);
            return rating;
        } else {
            return em.merge(rating);
        }
    }

    @Override
    public Rating get(int id) {
        return em.find(Rating.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Rating.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Rating> getAll() {
        return em.createNamedQuery(Rating.GET_ALL, Rating.class).getResultList();
    }

    @Override
    public List<Rating> getAllByRestaurant(int idRestaurant) {
        return em.createNamedQuery(Rating.GET_ALL_BY_RESTAURANT, Rating.class)
                .setParameter("id_restaurant", idRestaurant)
                .getResultList();
    }

    @Override
    public Rating getByRestaurantByDate(int idRest, LocalDate date) {
        List<Rating> ratings = em.createNamedQuery(Rating.GET_BY_RESTAURANT_BY_DATE)
                .setParameter("id_restaurant", idRest)
                .setParameter("date_vota", date)
                .getResultList();
        return DataAccessUtils.singleResult(ratings);
    }
}
