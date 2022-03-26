package io.github.mivek.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class ConverterTest {
    @Test
    void testConvertVisibility() {
        assertEquals(">10km", Converter.convertVisibility("9999"));
        assertEquals("5000m", Converter.convertVisibility("5000"));
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
}
