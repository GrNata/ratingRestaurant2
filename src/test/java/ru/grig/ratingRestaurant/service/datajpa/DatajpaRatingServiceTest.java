package ru.grig.ratingRestaurant.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.service.AbstractRatingServiceTest;

import static ru.grig.ratingRestaurant.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DatajpaRatingServiceTest extends AbstractRatingServiceTest {
}
