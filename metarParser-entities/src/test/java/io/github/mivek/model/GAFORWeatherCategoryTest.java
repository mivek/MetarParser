package io.github.mivek.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GAFORWeatherCategoryTest {

    @DisplayName("should check if criteria for weather category are met")
    @ParameterizedTest
    @MethodSource
    void testIsCriteriaMet(GAFORWeatherCategory weatherCategory, double visibility, int ceiling, boolean expected) {
        boolean result = weatherCategory.isCriteriaMet(visibility, ceiling);

        assertEquals(expected, result);
    }

    static Stream<Arguments> testIsCriteriaMet() {
        return Stream.of(
                Arguments.of(GAFORWeatherCategory.X, 1D, 300, true),
                Arguments.of(GAFORWeatherCategory.X, 1D, 600, false),
                Arguments.of(GAFORWeatherCategory.X, 2D, 300, false),
                Arguments.of(GAFORWeatherCategory.X, 2D, 600, false),
                Arguments.of(GAFORWeatherCategory.M2, 8D, 800, true),
                Arguments.of(GAFORWeatherCategory.M2, 7D, 800, false),
                Arguments.of(GAFORWeatherCategory.M2, 8D, 1500, false),
                Arguments.of(GAFORWeatherCategory.M2, 7D, 1500, false),
                Arguments.of(GAFORWeatherCategory.M5, 5D, 800, true),
                Arguments.of(GAFORWeatherCategory.M5, 8D, 800, false),
                Arguments.of(GAFORWeatherCategory.M5, 5D, 1500, false),
                Arguments.of(GAFORWeatherCategory.M5, 8D, 1500, false),
                Arguments.of(GAFORWeatherCategory.M5, 4D, 1500, false),
                Arguments.of(GAFORWeatherCategory.M5, 4D, 800, false),
                Arguments.of(GAFORWeatherCategory.M6, 1.5D, 2000, true),
                Arguments.of(GAFORWeatherCategory.M6, 1D, 2000, false),
                Arguments.of(GAFORWeatherCategory.M6, 1.5D, 1500, false),
                Arguments.of(GAFORWeatherCategory.M6, 1D, 1500, false),
                Arguments.of(GAFORWeatherCategory.M7, 1.5D, 1000, true),
                Arguments.of(GAFORWeatherCategory.M7, 5D, 1000, false),
                Arguments.of(GAFORWeatherCategory.M7, 1.5D, 2000, false),
                Arguments.of(GAFORWeatherCategory.M7, 5D, 2000, false),
                Arguments.of(GAFORWeatherCategory.M8, 1.5D, 800, true),
                Arguments.of(GAFORWeatherCategory.M8, 5D, 800, false),
                Arguments.of(GAFORWeatherCategory.M8, 1.5D, 1000, false),
                Arguments.of(GAFORWeatherCategory.M8, 5D, 1000, false),
                Arguments.of(GAFORWeatherCategory.D1, 8D, 1000, true),
                Arguments.of(GAFORWeatherCategory.D1, 5D, 1000, false),
                Arguments.of(GAFORWeatherCategory.D1, 8D, 800, false),
                Arguments.of(GAFORWeatherCategory.D1, 5D, 800, false),
                Arguments.of(GAFORWeatherCategory.D3, 5D, 2000, true),
                Arguments.of(GAFORWeatherCategory.D3, 2D, 2000, false),
                Arguments.of(GAFORWeatherCategory.D3, 5D, 1500, false),
                Arguments.of(GAFORWeatherCategory.D3, 2D, 1500, false),
                Arguments.of(GAFORWeatherCategory.D4, 5D, 1000, true),
                Arguments.of(GAFORWeatherCategory.D4, 10D, 1000, false),
                Arguments.of(GAFORWeatherCategory.D4, 5D, 2000, false),
                Arguments.of(GAFORWeatherCategory.D4, 10D, 2000, false),
                Arguments.of(GAFORWeatherCategory.O, 8D, 2000, true),
                Arguments.of(GAFORWeatherCategory.O, 2D, 2000, false),
                Arguments.of(GAFORWeatherCategory.O, 8D, 10000, false),
                Arguments.of(GAFORWeatherCategory.O, 2D, 10000, false),
                Arguments.of(GAFORWeatherCategory.C, 10D, 5000, true),
                Arguments.of(GAFORWeatherCategory.C, 9D, 5000, false),
                Arguments.of(GAFORWeatherCategory.C, 10D, 4000, false),
                Arguments.of(GAFORWeatherCategory.C, 9D, 4000, false));
    }
}
