package ru.grig.ratingRestaurant.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

//import org.junit.ClassRule;
//import org.junit.Rule;
//import org.junit.rules.ExternalResource;
//import org.junit.rules.Stopwatch;
//import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.grig.ratingRestaurant.ActiveDbProfileResolver;

//import static org.junit.Assert.assertThrows;
import static ru.grig.ratingRestaurant.util.ValidationUtil.getRootCause;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
//@RunWith(SpringRunner.class)

//@ExtendWith(SpringExtension.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ExtendWith(TimingExtension.class)
abstract public class AbstractServiceTest {



//    @ClassRule
//    public static ExternalResource summary = TimingRules.SUMMARY;
//
//    @Rule
//    public Stopwatch stopwatch = TimingRules.STOPWATCH;
//
//    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    public <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> rootExceptionClass) {
        assertThrows(rootExceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }

}