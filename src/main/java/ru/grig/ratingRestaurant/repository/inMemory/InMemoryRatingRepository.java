package ru.grig.ratingRestaurant.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryRatingRepository extends InMemoryBaseRepository<Rating> implements RatingRepository {
//public class InMemoryRatingRepository implements RatingRepository {
//public class InMemoryRatingRepository {
    private Map<Long, Rating> repository = new ConcurrentHashMap<>();
//    private Set<Rating> repository = new ConcurrentSkipListSet<>();
    AtomicLong counter = new AtomicLong(0);

    {
        save(new Rating(1, 12, LocalDate.of(2020, Month.MARCH, 30)));
        save(new Rating(2, 20, LocalDate.of(2020, Month.MARCH, 30)));
        save(new Rating(3, 8, LocalDate.of(2020, Month.MARCH, 30)));
        save(new Rating(1, 10, LocalDate.of(2020, Month.MAY, 10)));
        save(new Rating(2, 15, LocalDate.of(2020, Month.MAY, 10)));
        save(new Rating(3, 250, LocalDate.of(2020, Month.MAY, 10)));
        save(new Rating(1, 5, LocalDate.of(2020, Month.JUNE, 05)));
        save(new Rating(2, 22, LocalDate.of(2020, Month.JUNE, 05)));
        save(new Rating(3, 3, LocalDate.of(2020, Month.JUNE, 05)));
    }

    @Override
    public List<Rating> getAll() {
        return getCollection().stream()
                .collect(Collectors.toList());
    }

//    @Override
    public List<Rating> getAllByDate(LocalDate date) {
        return getCollection().stream()
                .filter(r -> r.getDateVote().equals(date))
                .collect(Collectors.toList());
    }

//    @Override
    public void setByVote(Long idRestaurant, LocalDate date) {
        int vote = 0;
        boolean isRest = true;
//        for (Map.Entry<Long, Rating> rat : repository.entrySet()) {
//            if (rat.getValue().getDateVote().equals(date) && rat.getValue().getIdRestaurant() == idRestaurant){
//                vote = rat.getValue().getCountVote();
//                vote++;
//                rat.getValue().setCountVote(vote);
//                isRest = false;
//                break;
//            }
//        }
        for (Rating rating : getAll()) {
            if (rating.getDateVote().equals(date) && rating.getIdRestaurant() == idRestaurant) {
                vote = rating.getCountVote();
                vote++;
                rating.setCountVote(vote);
                save(rating);
                isRest = false;
                break;
            }
        }
        if (isRest){
            vote++;
            save(new Rating(idRestaurant, vote, date));
        }
    }

//    @Override
    public int getRatingByRestaurant(Long idRestaurant) {
        int vote = 0;
        int voteAll = 0;
        for (Rating rat : getAll()) {
            voteAll = voteAll + rat.getCountVote();
            if (rat.getIdRestaurant() == idRestaurant){
                vote = vote + rat.getCountVote();
            }
        }
        return vote;        //  пока количество голосов, vote/voteAll*100 - процент
    }
}