package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId);

    Vote save2(Vote vote, User user);

    Vote get(int id, int userId) throws NotFoundException;

    boolean delete(int id, int userId);

    List<Vote> getAll();

    List<Vote> getAllByUser(int userId);

    Vote getByDate(int userId, LocalDate date);

}
