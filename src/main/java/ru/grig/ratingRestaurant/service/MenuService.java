package ru.grig.ratingRestaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

@Service
public class MenuService {

    private MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu create(Menu menu, int restId) {
        Assert.notNull(menu, "Restaurant must not be NULL");
        return menuRepository.save(menu, restId);
    }

    public Menu get(int id, int restId) {
        return checkNotFoundWithId(menuRepository.get(id, restId), id);
    }

    public void delete(int id, int restId) {
        checkNotFoundWithId(menuRepository.delete(id, restId), id);
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    public void update(Menu menu, int restId) {
        Assert.notNull(menu, "Restaurant must not be NULL");
        checkNotFoundWithId(menuRepository.save(menu, restId), menu.getId());
    }

    public List<Menu> getAllByRestaurant(int idRestaurant) {
        List<Menu> menus = menuRepository.getAllByRestaurant(idRestaurant);
        return menus.isEmpty() ? null : menus;

    }
}
