package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.repository.MenuRepository;

import java.util.List;

@Repository
public class DatajpaMenuRepository implements MenuRepository {

    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DatajpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Menu save(Menu menu, int restId) {
        if (!menu.isNew() && get(menu.getId(), restId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public Menu get(int id, int restId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restId)
                .orElse(null);
    }

    @Override
    public boolean delete(int id, int restId) {
        return crudMenuRepository.delete(id, restId) != 0;
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    @Override
    public List<Menu> getAllByRestaurant(int restId) {
        return crudMenuRepository.findAllByRestaurant(restId);
    }

}
