package ru.grig.ratingRestaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Rating;
import ru.grig.ratingRestaurant.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcRatingRepository implements RatingRepository {
    private static final BeanPropertyRowMapper<Rating> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Rating.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertRating;

    @Autowired

    public JdbcRatingRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertRating = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("rating")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Rating save(Rating rating) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", rating.getId())
                .addValue("idrestaurant", rating.getIdRestaurant())
                .addValue("countvote", rating.getCountVote())
                .addValue("datevote", rating.getDateVote());
        if (rating.isNew()) {
            Number newId = insertRating.executeAndReturnKey(map);
            rating.setId(newId.longValue());
        } else
            if (namedParameterJdbcTemplate.update("UPDATE rating SET " +
                    "idrestaurant=:idrestaurant, " +
                    "countvote=:countvote, " +
                    "datevote=:datevote WHERE id=:id", map)
            == 0) {
                return null;
            }
            return rating;
    }

    @Override
    public Rating get(long id) {
        List<Rating> ratingList = jdbcTemplate.query("" +
                "SELECT * FROM rating WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(ratingList);
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update("DELETE FROM rating WHERE id=?", id) != 0;
    }

    @Override
    public List<Rating> getAll() {
        return jdbcTemplate.query("SELECT * FROM rating", ROW_MAPPER);
    }

//    @Override
//    public void setByVote(Long idRestaurant, LocalDate date) {
//
//    }

//    @Override
//    public int getRatingByRestaurant(Long idRestaurant) {
//        return jdbcTemplate.query("SELECT * FROM rating WHERE idrestaurant=:id", ROW_MAPPER, idRestaurant);
//    }
}
