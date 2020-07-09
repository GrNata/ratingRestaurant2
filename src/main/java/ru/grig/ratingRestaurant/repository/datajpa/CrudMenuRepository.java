package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Menu;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restId")
    public int delete(@Param("id") int id, @Param("restId") int restId);

    @Query("select m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restId")
    public Menu getOne(@Param("id") int id, @Param("restId") int restId);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restId")
    public List<Menu> getAllByRestaurant(@Param("restId") int restId);
}
