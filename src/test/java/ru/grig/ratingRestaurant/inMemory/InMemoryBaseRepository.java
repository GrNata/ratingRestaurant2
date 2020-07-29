package ru.grig.ratingRestaurant.inMemory;

//import jdk.nashorn.internal.ir.IdentNode;
import ru.grig.ratingRestaurant.model.AbstractBaseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryBaseRepository<T extends AbstractBaseEntity> {
//    private AtomicLong counter = new AtomicInteger(0);
//    private long idRest = 1;
//
//    private Map<Long, T> mapEntry = new ConcurrentHashMap<>();
//
//    public T save(T entry, int idRest){
//        if (entry.isNew()){
//            entry.setId(counter.incrementAndGet());
//        }
//        return mapEntry.put(entry.getId(), entry);
//    }
//
//    public boolean delete(long id) {
//        return mapEntry.remove(id) != null;
//    }
//
//    public T get(long id) {
//        return mapEntry.get(id);
//    }
//
//    Collection<T> getCollection() {
//        return mapEntry.values();
//    }
}
