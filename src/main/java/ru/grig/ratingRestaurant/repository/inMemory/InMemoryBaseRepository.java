package ru.grig.ratingRestaurant.repository.inMemory;

import ru.grig.ratingRestaurant.model.AbstractBaseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryBaseRepository<T extends AbstractBaseEntity> {
    private AtomicLong counter = new AtomicLong(0);

    private Map<Long, T> mapEntry = new ConcurrentHashMap<>();

    public T save(T entry){
        if (entry.isNew()){
            entry.setId(counter.incrementAndGet());
        }
        return mapEntry.put(entry.getId(), entry);
    }

    public boolean delete(long id) {
        return mapEntry.remove(id) != null;
    }

    public T get(long id) {
        return mapEntry.get(id);
    }

    Collection<T> getCollection() {
        return mapEntry.values();
    }
}
