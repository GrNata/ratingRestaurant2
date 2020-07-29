package ru.grig.ratingRestaurant.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.grig.ratingRestaurant.service.AbstractUserServiceTest;
import ru.grig.ratingRestaurant.util.exception.NotFoundException;

import static ru.grig.ratingRestaurant.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DatajpaUserServiceTest extends AbstractUserServiceTest {

}
