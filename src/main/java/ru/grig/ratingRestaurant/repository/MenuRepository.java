package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Menu;

import java.util.Collection;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu);

    Menu get(long id);

    boolean delete(long id);

    List<Menu> getAll();

    List<Menu> getAllByRestaurant(long id);
}
