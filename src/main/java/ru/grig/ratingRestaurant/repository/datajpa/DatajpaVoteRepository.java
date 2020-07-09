package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.UserRepository;
import ru.grig.ratingRestaurant.repository.VoteRepository;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DatajpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DatajpaVoteRepository(CrudVoteRepository crudRepository, CrudUserRepository crudUserRepository,
                                 CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Vote save(Vote vote, int userId) {
        return null;
    }

        @Override
        public Vote save2(Vote vote, User user) {
        if (!vote.isNew() && get(vote.getId(), user.getId()) == null) {
            return null;
        }
        vote.setUser(user);
        return crudRepository.save(vote);
    }

    @Override
    public Vote get(int id, int userId) throws NotFoundException {
//        return crudRepository.get(id, userId);
//        return crudRepository.getOne(id, userId);
        Vote vote;
        if((vote = crudRepository.getOne(id, userId)) == null){
//        if (vote == null){
            throw new NotFoundException("NOT vote");
        }
        return vote;
    }

    @Override
    public boolean delete(int id, int userId) {
        boolean res;
        if((res = crudRepository.delete(id, userId) == 0)) {
            throw new NotFoundException("NOT VOTE");
        }
        return res;
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.getAll();
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return crudRepository.getAllByUser(userId);
    }

    @Override
    public Vote getByDate(int userId, LocalDate date) {
        return crudRepository.getByDate(userId, date);
    }
}
