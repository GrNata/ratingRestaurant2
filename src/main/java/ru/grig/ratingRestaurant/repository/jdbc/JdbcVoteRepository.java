package ru.grig.ratingRestaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.VoteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Repository
public class JdbcVoteRepository implements VoteRepository {
    private static final BeanPropertyRowMapper<Vote> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Vote.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertVote;

//    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired

    public JdbcVoteRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertVote = new SimpleJdbcInsert(jdbcTemplate)
                                .withTableName("vote")
                                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Vote save2(Vote vote, User user) {
        return null;
    }

    @Override
    public Vote save(Vote vote, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
//                .addValue("id_restaurant", vote.getIdRestaurant())
                .addValue("id_restaurant", vote.getRestaurant().getId())
                .addValue("vote_date_time", vote.getVoteDate())
                .addValue("id_user", userId);
        if (vote.isNew()) {
            Number newKey = insertVote.executeAndReturnKey(map);
            vote.setId(newKey.intValue());
        } else
            if (namedParameterJdbcTemplate.update(
                    "UPDATE vote SET " +
                            "id_restaurant=:id_restaurant, " +
                            "vote_date_time=:vote_date_time WHERE id=:id AND id_user=:id_user", map
            )
             == 0) {
                return null;
            }
        return vote;
    }

    @Override
    public Vote get(int id, int userId) {
        List<Vote> voteList = jdbcTemplate.query(
                "SELECT * FROM vote WHERE id=? AND id_user=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(voteList);
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update(
                "DELETE FROM vote WHERE id=? AND  id_user=?", id, userId) != 0;
    }

    @Override
    public List<Vote> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM vote", ROW_MAPPER);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return jdbcTemplate.query("SELECT * FROM vote WHERE id_user=?", ROW_MAPPER, userId);
    }

    @Override
    public Vote getByDate(int userId, LocalDate date) {
        return null;
    }
}
