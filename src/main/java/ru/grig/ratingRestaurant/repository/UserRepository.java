package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    User save(User user);

    User get(int id);

    boolean delete(int id);

    List<User> getAll();

    User getByEmail(String email);
}
