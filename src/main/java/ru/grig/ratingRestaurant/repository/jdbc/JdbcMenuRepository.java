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
    public Menu save(Menu menu, int restId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", menu.getId())
                .addValue("dishes", menu.getDishes())
                .addValue("price", menu.getPrice())
                .addValue("id_restaurant", restId);
        if (menu.isNew()) {
            Number newKey = insertMenu.executeAndReturnKey(map);
            menu.setId(newKey.intValue());
        } else
            if (namedParameterJdbcTemplate.update(
                    "UPDATE menu SET " +
//                            "idrestaurant=:idrestaurant, " +
                            "dishes=:dishes, " +
                            "price=:price WHERE id=:id AND id_restaurant=:id_restaurant", map) == 0) {
                return null;
            }
        return menu;
    }

    @Override
    public Menu get(int id, int restId) {
        List<Menu> menuList = jdbcTemplate.query(
          "SELECT * FROM menu WHERE id=? AND id_restaurant=?", ROW_MAPPER, id, restId);
        return DataAccessUtils.singleResult(menuList);
    }

    @Override
    public boolean delete(int id, int restId) {
        return jdbcTemplate.update(
                "DELETE FROM menu WHERE id=? AND id_restaurant=?", id, restId) != 0;
    }

    @Override
    public List<Menu> getAll() {
        return jdbcTemplate.query("SELECT * FROM menu", ROW_MAPPER);
    }

    @Override
    public List<Menu> getAllByRestaurant(int idRest) {
        return jdbcTemplate.query("SELECT * FROM  menu WHERE id_restaurant=?", ROW_MAPPER, idRest);
    }
}
