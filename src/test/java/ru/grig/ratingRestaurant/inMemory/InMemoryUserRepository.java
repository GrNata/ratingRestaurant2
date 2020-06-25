package ru.grig.ratingRestaurant.inMemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Role;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//@Repository
public class InMemoryUserRepository {
//public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
    private final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
//    private Map<Long, User> repository = new ConcurrentHashMap<>();
//    private AtomicLong counter = new AtomicLong(0);

//    {
//        save(new User(null,"Nata", "nata@gmail.com", "1111", LocalDate.of(2020, 02, 10), false, EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER)));
//        save(new User(null,"Dima", "dima@gmail.com", "2222", LocalDate.of(2020, 04, 15), false, Role.ROLE_USER));
//        save(new User(null,"Seva", "seva@gmail.com", "3333", LocalDate.of(2020, 03, 12), false, Role.ROLE_USER));
//    }

//    @Override
//    public User save(User user) {
//        if (user.isNew()) {
//            user.setId(counter.incrementAndGet());
//        }
//        return repository.put(user.getId(), user);
//    }
//
//    @Override
//    public User get(long id) {
//        return repository.get(id);
//    }
//
//    @Override
//    public boolean delete(long id) {
//        if (get(id) == null)    return false;
//        repository.remove(id);
//        return true;
//    }

//    @Override
//    public List<User> getAll() {
//            log.info("getAll");
////        return usersMap.values().stream()
//            return getCollection().stream()
//                    .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
//                    .collect(Collectors.toList());
//    }
//
//    @Override
//    public User getByEmail(String email) {
//        log.info("repository getEmail email - {}", email);
//        return getCollection().stream()
//                .filter(u -> email.equals(u.getEmail()))
//                .findFirst()
//                .orElse(null);
//    }
}
