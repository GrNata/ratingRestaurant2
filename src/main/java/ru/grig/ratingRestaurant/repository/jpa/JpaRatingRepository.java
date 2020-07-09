package ru.grig.ratingRestaurant.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRatingRepository implements RatingRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Rating save(Rating rating, int idRest) {
        rating.setRestaurant(em.getReference(Restaurant.class, idRest));
        if (rating.isNew()) {
            em.persist(rating);
            System.out.println("SAVE rating: "+rating);
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
        List<Rating> ratingList = em.createNamedQuery(Rating.GET_ALL_BY_RESTAURANT, Rating.class)
                .setParameter("id_restaurant", idRestaurant)
                .getResultList();
        System.out.println("ALL REPO: "+ratingList.size());
        return ratingList.size() != 0 ? ratingList : null;
    }

    @Override
//    public Rating getByRestaurantByDate(int idRest, LocalDate date) {
    public Rating getByRestaurantByDate(int idRest, LocalDateTime date) {
        List<Rating> ratings = em.createNamedQuery(Rating.GET_BY_RESTAURANT_BY_DATE)
                .setParameter("id_restaurant", idRest)
                .setParameter("date_vota", date)
                .getResultList();
        Rating rating = DataAccessUtils.singleResult(ratings);
        System.out.println("RATING REPO: "+rating);
        return rating != null ? rating : null;
    }

    @Override
    public List<Rating> getAllByDate(LocalDateTime dateTime) {
        List<Rating> ratingList = em.createNamedQuery(Rating.GET_ALL_BY_DATE)
                .setParameter("date", dateTime)
                .getResultList();
        return ratingList;
    }
}
