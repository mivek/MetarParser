package io.github.mivek.utils;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

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
}
