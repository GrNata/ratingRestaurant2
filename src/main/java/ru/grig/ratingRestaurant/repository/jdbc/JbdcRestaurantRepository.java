package ru.grig.ratingRestaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.model.Restaurant;
import ru.grig.ratingRestaurant.repository.RestaurantRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Repository
public class JbdcRestaurantRepository implements RestaurantRepository {
    private static final BeanPropertyRowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertRestaurant;

    @Autowired
    public JbdcRestaurantRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertRestaurant = new SimpleJdbcInsert(jdbcTemplate)
                                    .withTableName("restaurants")
                                    .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName())
                .addValue("menu", restaurant.getMenu())
                .addValue("rating", restaurant.getRating());
        if (restaurant.isNew()) {
            Number newKey = insertRestaurant.executeAndReturnKey(map);
            restaurant.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE restaurants SET name=:name, menu=:menu, rating=:rating WHERE id=:id", map) == 0) {
            return null;
        }
        return restaurant;
    }

    @Override
    public Restaurant get(int id) {
        List<Restaurant> restaurantList = jdbcTemplate.query(
                "SELECT * FROM restaurants WHERE id=?", ROW_MAPPER, id
        );
        return DataAccessUtils.singleResult(restaurantList);
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM restaurants WHERE id=?", id) != 0;
    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query("SELECT * FROM restaurants", ROW_MAPPER);
    }

    @Override       //  не используется, через доп. список каждый раз подсчет
    public void setRatingByRestaurant(Map<Integer, Integer> map_rating, LocalTime time) {
//        for (Map.Entry<Long, Integer> m : map_rating.entrySet()) {
//            jdbcTemplate.update("UPDATE restaurants SET rating=:m.getValue() WHERE id=?", m.getKey());
//        }
    }
}
