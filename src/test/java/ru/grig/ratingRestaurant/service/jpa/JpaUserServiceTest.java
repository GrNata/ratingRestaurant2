package ru.grig.ratingRestaurant.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.service.AbstractUserServiceTest;

import static ru.grig.ratingRestaurant.Profiles.*;

@ActiveProfiles({POSTGRES_DB, JPA})
public class JpaUserServiceTest extends AbstractUserServiceTest {
}
