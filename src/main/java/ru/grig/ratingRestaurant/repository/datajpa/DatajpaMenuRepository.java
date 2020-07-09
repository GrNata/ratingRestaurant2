package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.repository.MenuRepository;

import java.util.List;

@Repository
public class DatajpaMenuRepository implements MenuRepository {
//    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name");

    private final CrudMenuRepository crudRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DatajpaMenuRepository(CrudMenuRepository crudRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRepository = crudRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Menu save(Menu menu, int restId) {
        if (!menu.isNew() && get(menu.getId(), restId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restId));
        return crudRepository.save(menu);
    }

    @Override
    public Menu get(int id, int restId) {
        Menu menu = crudRepository.getOne(id, restId);
        return menu != null ? menu : null;
    }

    @Override
    public boolean delete(int id, int restId) {
        return crudRepository.delete(id, restId) != 0;
    }

    @Override
    public List<Menu> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public List<Menu> getAllByRestaurant(int restId) {
        return crudRepository.getAllByRestaurant(restId);
    }
}
