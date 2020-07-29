package ru.grig.ratingRestaurant.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.service.AbstractVoteServiceTest;

import static ru.grig.ratingRestaurant.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaVoteSErviceTest extends AbstractVoteServiceTest {
}
