package io.github.mivek.utils;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
    @Test
    public void testConvertVisibility() {
        assertEquals(">10km", Converter.convertVisibility("9999"));
        assertEquals("5000m", Converter.convertVisibility("5000"));
    }

    @Test
    public void testConvertTrend() {
        assertEquals(Messages.getInstance().getString("Converter.U"), Converter.convertTrend("U"));
        assertEquals(Messages.getInstance().getString("Converter.D"), Converter.convertTrend("D"));
        assertEquals(Messages.getInstance().getString("Converter.NSC"), Converter.convertTrend("N"));
        assertEquals("", Converter.convertTrend("Random string"));
    }

    @Test
    public void testInchesMercuryToHPascal() {
        assertEquals(1013.25, Converter.inchesMercuryToHPascal(29.92), 0.1);
    }

    @Test
    public void testConvertTemperature() {
        assertEquals(-10, Converter.convertTemperature("M10"));
        assertEquals(10, Converter.convertTemperature("10"));
    }

    @Test
    public void testStringToTime() {
        String input = "0830";
        LocalTime time = Converter.stringToTime(input);

        assertEquals(8, time.getHour());
        assertEquals(30, time.getMinute());
    }

    @Test
    public void testConvertIndicatorMinus() {
        assertEquals("less than", Converter.convertIndicator("M"));
    }

    @Test
    public void testConvertIndicatorPlus() {
        assertEquals("greater than", Converter.convertIndicator("P"));
    }

    @Test
    public void testConvertIndicatorNull() {
        assertEquals("", Converter.convertIndicator(null));
    }

    @Test
    public void testConvertPrecipitationAmount() {
        assertEquals(2.17, Converter.convertPrecipitationAmount("0217"), 1e-7);
    }

    @Test
    public void testConvertTemperaturePositive() {
        assertEquals(14.2, Converter.convertTemperature("0", "142"), 1e-1);
    }

    @Test
    public void testConvertTemperatureNegative() {
        assertEquals(-2.1, Converter.convertTemperature("1", "021"), 1e-1);
    }
}
