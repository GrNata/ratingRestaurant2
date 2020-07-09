package ru.grig.ratingRestaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.repository.UserRepository;

import java.util.List;
import static ru.grig.ratingRestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        log.info("create user: {}", user);
        Assert.notNull(user, "User must not be NULL");
        return userRepository.save(user);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Cacheable("users")
    public List<User> getAll(){
        return userRepository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "User must not be NULL");
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "Email must not be NULL");
        return userRepository.getByEmail(email);
    }
}
