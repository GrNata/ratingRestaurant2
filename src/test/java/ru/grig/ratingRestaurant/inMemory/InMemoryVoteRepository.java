package ru.grig.ratingRestaurant.inMemory;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.VoteRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//@Repository
public class InMemoryVoteRepository  {
//public class InMemoryVoteRepository extends InMemoryBaseRepository<Vote> implements VoteRepository {
    private Map<Long, Restaurant> repository = new ConcurrentHashMap<>();

    {
//        save(new Vote(1, 3, LocalDateTime.of(2020, Month.JUNE, 5, 10)));
//        save(new Vote(2, 2, LocalDateTime.of(2020, Month.JUNE, 5, 10)));
//        save(new Vote(3, 1, LocalDateTime.of(2020, Month.JUNE, 5, 10)));
    }

//    @Override
//    public List<Vote> getAll() {
//        return getCollection().stream()
//                .collect(Collectors.toList());
//    }

    //    @Override
//    public Vote save(Vote vote) {
//        return null;
//    }
//
//    @Override
//    public Vote get(long id) {
//        return null;
//    }
//
//    @Override
//    public boolean delete(long id) {
//        return false;
//    }
//
//    @Override
//    public Collection<Vote> getAll() {
//        return null;
//    }
}
