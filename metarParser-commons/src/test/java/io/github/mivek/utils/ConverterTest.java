package io.github.mivek.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    @Test
    void testConvertVisibility() {
        assertEquals(">10000", Converter.convertVisibility("9999"));
        assertEquals("5000", Converter.convertVisibility("5000"));
    }

    @Test
    void testInchesMercuryToHPascal() {
        assertEquals(1013.25, Converter.inchesMercuryToHPascal(29.92), 0.1);
    }

    @Test
    void testConvertTemperature() {
        assertEquals(-10, Converter.convertTemperature("M10"));
        assertEquals(10, Converter.convertTemperature("10"));
    }

    @Test
    void testStringToTime() {
        String input = "0830";
        LocalTime time = Converter.stringToTime(input);

        assertEquals(8, time.getHour());
        assertEquals(30, time.getMinute());
    }

    @Test
    void testConvertPrecipitationAmount() {
        assertEquals(2.17, Converter.convertPrecipitationAmount("0217"), 1e-7);
    }

    @Test
    void testConvertTemperaturePositive() {
        assertEquals(14.2, Converter.convertTemperature("0", "142"), 1e-1);
    }

    @Test
    void testConvertTemperatureNegative() {
        assertEquals(-2.1, Converter.convertTemperature("1", "021"), 1e-1);
    }

    @DisplayName("should convert visibility string to double value in km")
    @ParameterizedTest
    @MethodSource
    void testConvertVisibilityToKM(final String visibility, final Double expected) {
        final Double result = Converter.convertVisibilityToKM(visibility);

        assertEquals(expected, result);
    }

    @DisplayName("should convert km to statue miles")
    @Test
    void testKmToSM() {
        final Double result = Converter.kmToSM(2D);

        assertEquals(1.242742384474668, result);
    }

    static Stream<Arguments> testConvertVisibilityToKM() {
        return Stream.of(
                Arguments.of("notParsable", null),
                Arguments.of("2SM", 3.218688),
                Arguments.of("2KM", 2D),
                Arguments.of("800m", 0.8D),
                Arguments.of("2NM", null));
    }

    @DisplayName("should convert raw visibility string and unit shortcut to km")
    @ParameterizedTest
    @MethodSource
    void testConvertVisibilityToKMWithUnit(final String rawVisibility, final String unitShortcut, final Double expected) {
        final Double result = Converter.convertVisibilityToKM(rawVisibility, unitShortcut);

        if (expected == null) {
            assertNull(result);
        } else {
            assertNotNull(result);
            assertEquals(expected, result, 1e-6);
        }
    }

    static Stream<Arguments> testConvertVisibilityToKMWithUnit() {
        return Stream.of(
                Arguments.of("800", "M", 0.8D),
                Arguments.of(">10000", "M", 10.0D),
                Arguments.of("P6SM", "SM", 6 * 1.609344),
                Arguments.of("2", "KM", 2.0D),
                Arguments.of("notParsable", "M", null),
                Arguments.of("5000", "FT", null));
    }
}
