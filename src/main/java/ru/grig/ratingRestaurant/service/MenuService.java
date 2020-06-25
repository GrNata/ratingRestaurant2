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

    public Menu create(Menu menu, long restId) {
        Assert.notNull(menu, "Restaurant must not be NULL");
        return menuRepository.save(menu, restId);
    }

    public Menu get(long id, long restId) {
        return checkNotFoundWithId(menuRepository.get(id, restId), id);
    }

    public void delete(long id, long restId) {
        checkNotFoundWithId(menuRepository.delete(id, restId), id);
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    public void update(Menu menu, long restId) {
        Assert.notNull(menu, "Restaurant must not be NULL");
        checkNotFoundWithId(menuRepository.save(menu, restId), menu.getId());
    }

    public List<Menu> getAllByRestaurant(long idRestaurant) {
        List<Menu> menus = menuRepository.getAllByRestaurant(idRestaurant);
        return menus.isEmpty() ? null : menus;

    }
}
