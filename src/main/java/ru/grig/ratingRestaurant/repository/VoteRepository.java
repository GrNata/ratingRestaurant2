package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Vote;

import java.util.Collection;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId);

    Vote get(int id, int userId);

    boolean delete(int id, int userId);

    List<Vote> getAll();

    List<Vote> getAllByUser(int userId);

}
