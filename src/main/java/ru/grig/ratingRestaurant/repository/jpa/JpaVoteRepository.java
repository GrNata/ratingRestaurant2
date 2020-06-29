package ru.grig.ratingRestaurant.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
//        vote.setUser(em.getReference(User.class, userId));
//        System.out.println("VOTE: "+vote+"  USER: "+vote.getUser()+"  REST: "+vote.getRestaurant());
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public Vote get(int id, int userId) {
//        Vote vote = em.find(Vote.class, id);
//////        return vote != null && vote.getUser().getId() == userId ? vote : null;
//        return vote != null && vote.getIdUser() == userId ? vote : null;
        List<Vote> votes = em.createNamedQuery(Vote.GET)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .getResultList();
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.GET_ALL)
                .getResultList();
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return em.createNamedQuery(Vote.GET_ALL_BY_USER)
                .setParameter("user_id", userId)
                .getResultList();
    }
}
