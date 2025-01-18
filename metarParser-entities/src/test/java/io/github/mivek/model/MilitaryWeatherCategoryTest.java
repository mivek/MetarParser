package io.github.mivek.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MilitaryWeatherCategoryTest {

    @DisplayName("should check if criteria for weather category are met")
    @ParameterizedTest
    @MethodSource
    void testIsCriteriaMet(MilitaryWeatherCategory weatherCategory, double visibility, int ceiling, boolean expected) {
        boolean result = weatherCategory.isCriteriaMet(visibility, ceiling);

        assertEquals(expected, result);
    }

    static Stream<Arguments> testIsCriteriaMet() {
        return Stream.of(
                Arguments.of(MilitaryWeatherCategory.RED, 0.7D, 100, true),
                Arguments.of(MilitaryWeatherCategory.RED, 0.7D, 200, false),
                Arguments.of(MilitaryWeatherCategory.RED, 0.8D, 100, false),
                Arguments.of(MilitaryWeatherCategory.RED, 0.8D, 200, false),
                Arguments.of(MilitaryWeatherCategory.AMB, 0.8D, 200, true),
                Arguments.of(MilitaryWeatherCategory.AMB, 0.8D, 300, false),
                Arguments.of(MilitaryWeatherCategory.AMB, 1.6D, 200, false),
                Arguments.of(MilitaryWeatherCategory.AMB, 1.6D, 300, false),
                Arguments.of(MilitaryWeatherCategory.AMB, 0.8D, 100, false),
                Arguments.of(MilitaryWeatherCategory.AMB, 0.5D, 200, false),
                Arguments.of(MilitaryWeatherCategory.AMB, 0.5D, 300, false),
                Arguments.of(MilitaryWeatherCategory.YLO, 1.6D, 300, true),
                Arguments.of(MilitaryWeatherCategory.YLO, 1.6D, 700, false),
                Arguments.of(MilitaryWeatherCategory.YLO, 3.7D, 300, false),
                Arguments.of(MilitaryWeatherCategory.YLO, 3.7D, 700, false),
                Arguments.of(MilitaryWeatherCategory.YLO, 1.6D, 200, false),
                Arguments.of(MilitaryWeatherCategory.YLO, 1.5D, 300, false),
                Arguments.of(MilitaryWeatherCategory.YLO, 1.5D, 200, false),
                Arguments.of(MilitaryWeatherCategory.GRN, 3.7D, 700, true),
                Arguments.of(MilitaryWeatherCategory.GRN, 3.7D, 1500, false),
                Arguments.of(MilitaryWeatherCategory.GRN, 5D, 700, false),
                Arguments.of(MilitaryWeatherCategory.GRN, 5D, 1500, false),
                Arguments.of(MilitaryWeatherCategory.GRN, 3.7D, 600, false),
                Arguments.of(MilitaryWeatherCategory.GRN, 3.5D, 700, false),
                Arguments.of(MilitaryWeatherCategory.GRN, 3.5D, 1500, false),
                Arguments.of(MilitaryWeatherCategory.WHT, 5D, 1500, true),
                Arguments.of(MilitaryWeatherCategory.WHT, 5D, 2500, false),
                Arguments.of(MilitaryWeatherCategory.WHT, 8D, 1500, false),
                Arguments.of(MilitaryWeatherCategory.WHT, 8D, 2500, false),
                Arguments.of(MilitaryWeatherCategory.WHT, 5D, 1000, false),
                Arguments.of(MilitaryWeatherCategory.WHT, 4D, 1500, false),
                Arguments.of(MilitaryWeatherCategory.WHT, 4D, 1000, false),
                Arguments.of(MilitaryWeatherCategory.BLU, 8D, 2500, true),
                Arguments.of(MilitaryWeatherCategory.BLU, 8D, 1000, false),
                Arguments.of(MilitaryWeatherCategory.BLU, 7D, 2500, false),
                Arguments.of(MilitaryWeatherCategory.BLU, 7D, 1000, false));
    }
}
