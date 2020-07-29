package ru.grig.ratingRestaurant.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.service.AbstractMenuServiceTest;

import static ru.grig.ratingRestaurant.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaMenuServiceTest extends AbstractMenuServiceTest {
}
