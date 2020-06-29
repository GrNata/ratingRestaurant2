package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Menu;

import java.util.Collection;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restId);

    Menu get(int id, int restId);

    boolean delete(int id, int restId);

    List<Menu> getAll();

    List<Menu> getAllByRestaurant(int restId);
}
