package ru.grig.ratingRestaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.grig.ratingRestaurant.controller.menu.MenuRestController;
import ru.grig.ratingRestaurant.model.Menu;
import ru.grig.ratingRestaurant.repository.MenuRepository;

import java.util.List;

@Repository
public class JdbcMenuRepository implements MenuRepository {
    private static final BeanPropertyRowMapper<Menu> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Menu.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertMenu;

    @Autowired

    public JdbcMenuRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMenu = new SimpleJdbcInsert(jdbcTemplate)
                                .withTableName("menu")
                                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Menu save(Menu menu) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", menu.getId())
                .addValue("idrestaurant", menu.getIdRestaurant())
                .addValue("dishes", menu.getDishes())
                .addValue("price", menu.getPrice());
        if (menu.isNew()) {
            Number newKey = insertMenu.executeAndReturnKey(map);
            menu.setId(newKey.longValue());
        } else
            if (namedParameterJdbcTemplate.update(
                    "UPDATE menu SET " +
                            "idrestaurant=:idrestaurant, " +
                            "dishes=:dishes, " +
                            "price=:price WHERE id=:id", map) == 0) {
                return null;
            }
        return menu;
    }

    @Override
    public Menu get(long id) {
        List<Menu> menuList = jdbcTemplate.query(
          "SELECT * FROM menu WHERE id=?", ROW_MAPPER, id
        );
        return DataAccessUtils.singleResult(menuList);
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(
                "DELETE FROM menu WHERE id=?", id
        ) != 0;
    }

    @Override
    public List<Menu> getAll() {
        return jdbcTemplate.query("SELECT * FROM menu", ROW_MAPPER);
    }

    @Override
    public List<Menu> getAllByRestaurant(long idRest) {
        return jdbcTemplate.query("SELECT * FROM  menu WHERE idrestaurant=?", ROW_MAPPER, idRest);
    }
}
