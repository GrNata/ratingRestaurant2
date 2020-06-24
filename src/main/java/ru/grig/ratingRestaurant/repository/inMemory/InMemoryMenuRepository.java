package ru.grig.ratingRestaurant.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryMenuRepository extends InMemoryBaseRepository<Menu> implements MenuRepository {
//    private Map<Long, Menu> repository = new ConcurrentHashMap<>();
//    AtomicLong counter = new AtomicLong(0);

    {
        save(new Menu(1l, "dish 1", 100));
        save(new Menu(1l, "dish 2", 150));
        save(new Menu(1l, "dish 3", 130));
        save(new Menu(2l, "2dish 1", 200));
        save(new Menu(2l, "2dish 2", 250));
        save(new Menu(2l, "2dish 3", 230));
        save(new Menu(3l, "3dish 1", 300));
        save(new Menu(3l, "3dish 2", 350));
        save(new Menu(3l, "3dish 3", 320));
    }

//    @Override
//    public Menu save(Menu menu) {
//        if (menu.isNew()) {
//            menu.setId(counter.incrementAndGet());
//        }
//        return repository.put(menu.getId(), menu);
//    }
//
//    @Override
//    public Menu get(long id) {
//        return repository.get(id);
//    }
//
//    @Override
//    public void delete(long id) {
//        repository.remove(id);
//    }

    @Override
    public List<Menu> getAll() {
//        return repository.values();
        return getCollection().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> getAllByRestaurant(long idRestaurant) {
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : getAll()) {
            if (menu.getIdRestaurant() == idRestaurant)
                menus.add(menu);
        }
        return menus;
    }
}
