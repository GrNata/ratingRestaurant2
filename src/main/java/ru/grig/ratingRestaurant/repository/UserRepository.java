package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    User save(User user);

    User get(long id);

    boolean delete(long id);

    List<User> getAll();

    User getByEmail(String email);
}
