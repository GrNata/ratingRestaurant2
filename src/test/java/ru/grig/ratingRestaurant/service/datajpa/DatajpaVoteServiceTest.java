package ru.grig.ratingRestaurant.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.service.AbstractVoteServiceTest;

import static ru.grig.ratingRestaurant.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DatajpaVoteServiceTest extends AbstractVoteServiceTest {
}
