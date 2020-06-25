package ru.grig.ratingRestaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Vote;
import ru.grig.ratingRestaurant.repository.VoteRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
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
    public Vote save(Vote vote) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
                .addValue("iduser", vote.getIdUser())
                .addValue("idrestaurant", vote.getIdRestaurant())
                .addValue("votedatetime", vote.getVoteDate());
        if (vote.isNew()) {
            Number newKey = insertVote.executeAndReturnKey(map);
            vote.setId(newKey.longValue());
        } else
            if (namedParameterJdbcTemplate.update(
                    "UPDATE vote SET " +
                            "iduser=:iduser, " +
                            "idrestaurant=:idrestaurant, " +
                            "votedatetime=:votedatetime WHERE id=:id", map
            )
             == 0) {
                return null;
            }
        return vote;
    }

    @Override
    public Vote get(long id) {
        List<Vote> voteList = jdbcTemplate.query(
                "SELECT * FROM vote WHERE id=?", ROW_MAPPER, id
        );
        return DataAccessUtils.singleResult(voteList);
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(
                "DELETE FROM vote WHERE id=?", id
        ) != 0;
    }

    @Override
    public List<Vote> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM vote", ROW_MAPPER
        );
    }
}
