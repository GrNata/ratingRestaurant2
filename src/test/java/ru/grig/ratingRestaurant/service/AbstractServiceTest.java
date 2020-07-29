package ru.grig.ratingRestaurant.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import org.junit.runner.RunWith;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.grig.ratingRestaurant.ActiveDbProfileResolver;
import ru.grig.ratingRestaurant.TimingExtension;

//import static ru.grig.ratingRestaurant.Profiles.JDBC;
import static ru.grig.ratingRestaurant.Profiles.POSTGRES_DB;

//@ContextConfiguration({
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
//@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@ExtendWith(TimingExtension.class)
public abstract class AbstractServiceTest {
}
