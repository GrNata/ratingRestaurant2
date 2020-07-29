package ru.grig.ratingRestaurant;

import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMatcher<T> {

    private final Class<T> clazz;
    private final String[] fieldsToIgnore;
    private final boolean usingEquals;

    private TestMatcher(Class<T> clazz, boolean usingEquals, String... fieldsToIgnore) {
        this.clazz = clazz;
        this.fieldsToIgnore = fieldsToIgnore;
        this.usingEquals = usingEquals;
    }

    public static <T> TestMatcher<T> usingEquals(Class<T> clazz) {
        return new TestMatcher<>(clazz, true);
    }

    public static <T> TestMatcher<T> usingFieldsComparator(Class<T> clazz, String... fieldsToIgnore) {

        System.out.println("USING_FILE clazz = "+clazz + ", fields: ");
        for (String s : fieldsToIgnore) System.out.println(fieldsToIgnore);

        return new TestMatcher<>(clazz, false, fieldsToIgnore);
    }

    public void assertMatch(T actual, T expected) {
//        assertThat(actual).isEqualToIgnoringGivenFields(expected, fieldsToIgnore);
        if (usingEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).isEqualToIgnoringGivenFields(expected, fieldsToIgnore);
        }
    }

    public void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, Arrays.asList(expected));

    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
//        assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
        if (usingEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
        }

    }

    // ??????????
    public void assertMatch(Integer actual, Integer expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public ResultMatcher contentJson(T expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, clazz), expected);
    }

    public ResultMatcher contentJson(T... expected) {
        return result -> assertMatch(List.of(expected));
    }

    public ResultMatcher contentJson(Iterable<T> expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, clazz), expected);
    }

}
