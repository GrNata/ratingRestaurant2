package ru.grig.ratingRestaurant.service;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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

    public User create(User user) {
        log.info("create user: {}", user);
        return userRepository.save(user);
    }

    public User get(long id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public void delete(long id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public void update(User user) {
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }
}
