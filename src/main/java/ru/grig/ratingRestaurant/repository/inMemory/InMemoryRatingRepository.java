package ru.grig.ratingRestaurant.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryRatingRepository extends InMemoryBaseRepository<Rating> implements RatingRepository {
//    private Map<Long, Rating> repository = new ConcurrentHashMap<>();
//    AtomicLong counter = new AtomicLong(0);

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


//    @Override
//    public Rating save(Rating rating) {
//        if (rating.isNew()){
//            rating.setId(counter.incrementAndGet());
//        }
//        return repository.put(rating.getId(), rating);
//    }
//
//    @Override
//    public Rating get(Long id) {
//        return repository.get(id);
//    }
//
//    @Override
//    public void delete(Long id) {
//        repository.remove(id);
//    }

    @Override
    public List<Rating> getAll() {
//        return repository.values();
        return getCollection().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Rating> getAllByDate(LocalDate date) {
//        List<Rating> result = new ArrayList<>();
//        for (Rating rat : getAll()) {
//            if (rat.getDateVote().equals(date)) {
//                result.add(rat);
//            }
//        }
//        return result;
        return getCollection().stream()
                .filter(r -> r.getDateVote().equals(date))
                .collect(Collectors.toList());
    }

//    @Override
//    public void setByVote(Long idRestaurant, LocalDate date) {
//        int vote = 0;
//        boolean isRest = true;
////        for (Map.Entry<Long, Rating> rat : repository.entrySet()) {
////            if (rat.getValue().getDateVote().equals(date) && rat.getValue().getIdRestaurant() == idRestaurant){
////                vote = rat.getValue().getCountVote();
////                vote++;
////                rat.getValue().setCountVote(vote);
////                isRest = false;
////                break;
////            }
////        }
//        for (Rating rating : getAll()) {
//            if (rating.getDateVote().equals(date) && rating.getIdRestaurant() == idRestaurant) {
//                vote = rating.getCountVote();
//                vote++;
//                rating.setCountVote(vote);
//                save(rating);
//                isRest = false;
//                break;
//            }
//        }
//        if (isRest){
//            vote++;
//            save(new Rating(idRestaurant, vote, date));
//        }
//    }
//
//    @Override
//    public int getRatingByRestaurant(Long idRestaurant) {
//        int vote = 0;
//        int voteAll = 0;
//        for (Rating rat : getAll()) {
//            voteAll = voteAll + rat.getCountVote();
//            if (rat.getIdRestaurant() == idRestaurant){
//                vote = vote + rat.getCountVote();
//            }
//        }
//        return vote;        //  пока количество голосов, vote/voteAll*100 - процент
//    }
}
