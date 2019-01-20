package com.mivek.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import internationalization.Messages;

public class ConverterTest {

    @Test
    public void testdegreesToDirectionVRB() {
        String res = Converter.degreesToDirection("anyString");
        assertEquals(Messages.getInstance().getConverterVRB(), res);
    }

    @Test
    public void testdegreesToDirectionN() {
        String res1 = Converter.degreesToDirection("2");
        String res2 = Converter.degreesToDirection("345");

        assertEquals(Messages.getInstance().getConverterN(), res1);
        assertEquals(Messages.getInstance().getConverterN(), res2);
    }

    @Test
    public void testToDirection() {
        assertEquals(Messages.getInstance().getConverterE(), Converter.degreesToDirection("80"));
        assertEquals(Messages.getInstance().getConverterNE(), Converter.degreesToDirection("30"));
        assertEquals(Messages.getInstance().getConverterS(), Converter.degreesToDirection("200"));
        assertEquals(Messages.getInstance().getConverterW(), Converter.degreesToDirection("280"));
        assertEquals(Messages.getInstance().getConverterNW(), Converter.degreesToDirection("300"));
        assertEquals(Messages.getInstance().getConverterSE(), Converter.degreesToDirection("130"));
        assertEquals(Messages.getInstance().getConverterSW(), Converter.degreesToDirection("230"));
    }

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
    public void testMercuryToPascal() {
        assertEquals(1018, Converter.mercuryToPascal("3006"), 1);
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
