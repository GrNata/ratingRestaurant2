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
import ru.grig.ratingRestaurant.repository.UserRepository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {
    private static final BeanPropertyRowMapper ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("registered", user.getRegistered());
//                .addValue("enable", user.isEnable());
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
        } else
            if (namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered WHERE id=:id", map) == 0) {
                return null;
            }
        return user;
    }

    @Override
    public User get(int id) {
        List<User> userList = jdbcTemplate.query(
                "SELECT * FROM users WHERE id=?", ROW_MAPPER, id
        );
        return DataAccessUtils.singleResult(userList);
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update(
                "DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM users ORDER BY name, email ", ROW_MAPPER
        );
    }

    @Override
    public User getByEmail(String email) {
        List<User> userList = jdbcTemplate.query(
                "SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return DataAccessUtils.singleResult(userList);
    }
}
