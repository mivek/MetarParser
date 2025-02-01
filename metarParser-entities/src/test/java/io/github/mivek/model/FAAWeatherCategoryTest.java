package io.github.mivek.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FAAWeatherCategoryTest {

    @DisplayName("should check if criteria for weather category are met")
    @ParameterizedTest
    @MethodSource
    void testIsCriteriaMet(FAAWeatherCategory weatherCategory, double visibility, int ceiling, boolean expected) {
        boolean result = weatherCategory.isCriteriaMet(visibility, ceiling);

        assertEquals(expected, result);
    }

    static Stream<Arguments> testIsCriteriaMet() {
        return Stream.of(
                Arguments.of(FAAWeatherCategory.LIFR, 1D, 400, true),
                Arguments.of(FAAWeatherCategory.LIFR, 5D, 400, true),
                Arguments.of(FAAWeatherCategory.LIFR, 1D, 800, true),
                Arguments.of(FAAWeatherCategory.LIFR, 5D, 800, false),

                Arguments.of(FAAWeatherCategory.IFR, 3.3D, 800, true),
                Arguments.of(FAAWeatherCategory.IFR, 10D, 800, true),
                Arguments.of(FAAWeatherCategory.IFR, 3.3D, 1500, true),
                Arguments.of(FAAWeatherCategory.IFR, 10D, 1500, false),

                Arguments.of(FAAWeatherCategory.IFR, 0.5D, 400, false),
                Arguments.of(FAAWeatherCategory.IFR, 3.3D, 800, true),
                Arguments.of(FAAWeatherCategory.IFR, 9.5D, 800, true),

                Arguments.of(FAAWeatherCategory.MVFR, 6.5D, 1500, true),
                Arguments.of(FAAWeatherCategory.MVFR, 10D, 1500, true),
                Arguments.of(FAAWeatherCategory.MVFR, 6.5D, 3500, true),
                Arguments.of(FAAWeatherCategory.MVFR, 10D, 3500, false),

                Arguments.of(FAAWeatherCategory.MVFR, 4D, 1500, true),
                Arguments.of(FAAWeatherCategory.MVFR, 6.5D, 500, true),
                Arguments.of(FAAWeatherCategory.MVFR, 4D, 500, false),

                Arguments.of(FAAWeatherCategory.VFR, 10D, 3500, true),
                Arguments.of(FAAWeatherCategory.VFR, 2D, 3500, false),
                Arguments.of(FAAWeatherCategory.VFR, 10D, 800, false),
                Arguments.of(FAAWeatherCategory.VFR, 2D, 800, false));
    }
}
