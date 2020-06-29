package ru.grig.ratingRestaurant.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.MenuRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class JpaMenuRepositpry  implements MenuRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu, int restId) {
        menu.setRestaurant(em.getReference(Restaurant.class, restId));
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Override
    public Menu get(int id, int restId) {
        em.find(Restaurant.class, restId);
        Menu menu = em.find(Menu.class, id);
//        List<Menu> menuList = em.createNamedQuery(Menu.GET)
//                .setParameter("id", id)
////                .setParameter("id_restaurant", restId)
//                .getResultList();
//        Menu menu = DataAccessUtils.singleResult(menuList);
        return menu != null && menu.getRestaurant().getId() == restId ? menu : null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int restId) {
//        em.find(Restaurant.class, restId);
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .setParameter("id_restaurant", restId)
                .executeUpdate() != 0;
    }

    @Override
    public List<Menu> getAll() {
//        em.createNativeQuery("SELECT r FROM Restaurant r");
//        em.createNamedQuery(Restaurant.getALL);
        return em.createNamedQuery(Menu.GET_ALL, Menu.class).getResultList();
    }

    @Override
    public List<Menu> getAllByRestaurant(int restId) {
        em.find(Restaurant.class, restId);
        return em.createNamedQuery(Menu.GET_ALL_BY_RESTAURANT)
                .setParameter("id_restaurant", restId)
                .getResultList();
    }
}
