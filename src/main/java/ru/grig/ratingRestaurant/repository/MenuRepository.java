package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Menu;

import java.util.Collection;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, long restId);

    Menu get(long id, long restId);

    boolean delete(long id, long restId);

    List<Menu> getAll();

    List<Menu> getAllByRestaurant(long restId);
}
