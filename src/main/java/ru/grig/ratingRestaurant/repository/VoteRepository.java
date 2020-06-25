package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Vote;

import java.util.Collection;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, long userId);

    Vote get(long id, long userId);

    boolean delete(long id, long userId);

    List<Vote> getAll();

    List<Vote> getAllByRest(long userId);

}
