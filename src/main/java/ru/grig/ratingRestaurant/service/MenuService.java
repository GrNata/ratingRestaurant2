package ru.grig.ratingRestaurant.service;

import org.springframework.stereotype.Service;
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

    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu get(long id) {
        return checkNotFoundWithId(menuRepository.get(id), id);
    }

    public void delete(long id) {
        checkNotFoundWithId(menuRepository.delete(id), id);
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    public void update(Menu menu) {
        checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }

    public List<Menu> getAllByRestaurant(long idRestaurant) {
        return menuRepository.getAllByRestaurant(idRestaurant);
//        List<Menu> menus = new ArrayList<>();
//        for (Menu menu : getAll()) {
//            if (menu.getIdRestaurant() == idRestaurant)
//                menus.add(menu);
//        }
//        return menus;
    }
}
