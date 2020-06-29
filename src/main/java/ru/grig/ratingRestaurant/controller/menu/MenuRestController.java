package ru.grig.ratingRestaurant.controller.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.service.MenuService;
import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

import java.util.List;

@Controller
public class MenuRestController {
    private final Logger log = LoggerFactory.getLogger(MenuRestController.class);

    @Autowired
    private final MenuService menuService;

    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    public Menu get(int id, int restId) {
//        log.info("get id {}", id);
        return menuService.get(id, restId);
    }

    public void delete(int id, int restId) {
//        log.info("delete id {}", id);
        menuService.delete(id, restId);
    }

    public List<Menu> getAll() {
//        log.info("getAll");
        return menuService.getAll();
    }

    public Menu create(Menu menu, int restId) {
//        log.info("create menu: {}", menu);
        checkNew(menu);
        return menuService.create(menu, restId);
    }

    public  void update(Menu menu, int restId) {
//        log.info("update menu: {}", menu);
        assureIdConsistent(menu, menu.getId());
        menuService.update(menu, restId);
    }

    public List<Menu> getAllByRestaurant(int idRestaurant) {
        log.info("getAllByRestaurant id {}", idRestaurant);
        return menuService.getAllByRestaurant(idRestaurant);
    }
}
