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
import java.time.LocalDateTime;
import java.util.List;

//@Repository
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
    public Rating save(Rating rating, int idRest) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", rating.getId())
//                .addValue("id_restaurant", rating.getIdRestaurant())
                .addValue("id_restaurant", rating.getRestaurant().getId())
                .addValue("count_vote", rating.getCountVote())
                .addValue("date_vote", rating.getDateVote());
//                .addValue("id_restaurant", rating.getIdRestaurant());
        if (rating.isNew()) {
            Number newId = insertRating.executeAndReturnKey(map);
            rating.setId(newId.intValue());
        } else
            if (namedParameterJdbcTemplate.update("UPDATE rating SET " +
                    "id_restaurant=:id_restaurant, " +
                    "count_vote=:count_vote, " +
                    "date_vote=:date_vote WHERE id=:id", map)
            == 0) {
                return null;
            }
            return rating;
    }

    @Override
    public Rating get(int id) {
        List<Rating> ratingList = jdbcTemplate.query("" +
                "SELECT * FROM rating WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(ratingList);
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM rating WHERE id=?", id) != 0;
    }

    @Override
    public List<Rating> getAll() {
        return jdbcTemplate.query("SELECT * FROM rating", ROW_MAPPER);
    }

    @Override
    public List<Rating> getAllByRestaurant(int idRestaurant) {
        return null;
    }

    @Override
    public Rating getByRestaurantByDate(int idRest, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Rating> getAllByDate(LocalDateTime dateTime) {
        return null;
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
