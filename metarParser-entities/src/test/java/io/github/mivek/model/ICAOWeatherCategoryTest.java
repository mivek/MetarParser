package io.github.mivek.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ICAOWeatherCategoryTest {

    @DisplayName("should check if criteria for weather category are met")
    @ParameterizedTest
    @MethodSource
    void testIsCriteriaMet(ICAOWeatherCategory weatherCategory, double visibility, int ceiling, boolean expected) {
        boolean result = weatherCategory.isCriteriaMet(visibility, ceiling);

        assertEquals(expected, result);
    }

    static Stream<Arguments> testIsCriteriaMet() {
        return Stream.of(
                Arguments.of(ICAOWeatherCategory.IMC, 1D, 300, true),
                Arguments.of(ICAOWeatherCategory.IMC, 1D, 1500, true),
                Arguments.of(ICAOWeatherCategory.IMC, 5D, 300, true),
                Arguments.of(ICAOWeatherCategory.IMC, 5D, 1500, false),
                Arguments.of(ICAOWeatherCategory.VMC, 5D, 1500, true),
                Arguments.of(ICAOWeatherCategory.VMC, 5D, 800, false),
                Arguments.of(ICAOWeatherCategory.VMC, 4D, 1500, false),
                Arguments.of(ICAOWeatherCategory.VMC, 4D, 1500, false));
    }
}
