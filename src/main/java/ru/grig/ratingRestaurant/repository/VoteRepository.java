package ru.grig.ratingRestaurant.repository;

import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.model.Vote;

import java.util.Collection;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    Vote get(long id);

    boolean delete(long id);

    List<Vote> getAll();

}