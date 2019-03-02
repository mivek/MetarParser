package io.github.mivek.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import io.github.mivekinternationalization.Messages;

public class ConverterTest {
    @Test
    public void testBetween() {

        assertTrue(Converter.isBetween(5, 2, 8));
        assertTrue(Converter.isBetween(5, 5, 8));
        assertFalse(Converter.isBetween(5, 6, 10));
        assertFalse(Converter.isBetween(5, 0, 2));
    }

    @Test
    public void testConvertVisibility() {
        assertEquals(">10km", Converter.convertVisibility("9999"));
        assertEquals("5000m", Converter.convertVisibility("5000"));
    }

    @Test
    public void testConvertTrend() {
        assertEquals(Messages.getInstance().getConverterU(), Converter.convertTrend("U"));
        assertEquals(Messages.getInstance().getConverterD(), Converter.convertTrend("D"));
        assertEquals(Messages.getInstance().getConverterNSC(), Converter.convertTrend("N"));
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
