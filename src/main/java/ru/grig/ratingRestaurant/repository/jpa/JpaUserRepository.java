package ru.grig.ratingRestaurant.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            System.out.println("USER: "+user);
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
 //        User ref =em.getReference(User.class, id);
//        em.remove(ref);
//        System.out.println("REMOVE!!!!!");
//        return true;

//        Query query =em.createQuery("DELETE FROM User u WHERE u.id=:id");
//        return query.setParameter("id", id).executeUpdate() != 0;

        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED).getResultList();
    }

    @Override
    public User getByEmail(String email) {
        List<User> userList = em.createNamedQuery(User.BY_EMAIL)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(userList);
    }
}
