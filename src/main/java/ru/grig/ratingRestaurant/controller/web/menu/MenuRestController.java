package ru.grig.ratingRestaurant.controller.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.service.MenuService;
import static ru.grig.ratingRestaurant.util.ValidationUtil.*;

import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL_MENU, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController extends AbstractMenuController {

    static final String REST_URL_MENU = "/rest/profile/restaurants/menus";

    private final Logger log = LoggerFactory.getLogger(MenuRestController.class);

//    @Autowired
//    private final MenuService menuService;
//
//    public MenuRestController(MenuService menuService) {
//        this.menuService = menuService;
//    }

//    @Override
//    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Menu get(@RequestParam int id, @RequestBody int restId) {
//        log.info("get id {}", id);
//        return super.get(id, restId);
//    }
//
//    @Override
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@RequestParam int id, int restId) {
//        log.info("delete id {}", id);
//        super.delete(id, restId);
//    }
//
//    @GetMapping
//    public List<Menu> getAll() {
//        log.info("getAll");
//        return super.getAll();
//    }
//
////    public Menu create(Menu menu, int restId) {
//////        log.info("create menu: {}", menu);
////        checkNew(menu);
////        return menuService.create(menu, restId);
////    }
//
//    @Override
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public  void update(@RequestBody Menu menu, @RequestParam int restId) {
//        log.info("update menu: {}", menu);
////        assureIdConsistent(menu, menu.getId());
//        super.update(menu, restId);
//    }

    @Override
    @GetMapping("/{id}")
    public List<Menu> getAllByRestaurant(@RequestParam int idRestaurant) {
        log.info("getAllByRestaurant id {}", idRestaurant);
        return super.getAllByRestaurant(idRestaurant);
    }
}
