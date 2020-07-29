package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.VoteRepository;

import java.util.List;

@Repository
public class DatajpaVoteRepository implements VoteRepository {

    private final CrudVoteRepositiry crudVoteRepositiry;

    public DatajpaVoteRepository(CrudVoteRepositiry crudVoteRepositiry) {
        this.crudVoteRepositiry = crudVoteRepositiry;
    }

    @Override
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setIdUser(userId);
        return crudVoteRepositiry.save(vote);
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepositiry.findByIdAndIdUser(id, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepositiry.deleteByIdAndIdUser(id, userId) != 0;
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepositiry.findAll();
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return crudVoteRepositiry.findAllByIdUser(userId);
    }
}
