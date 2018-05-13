package com.mivek.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import i18n.Messages;

public class ConverterTest {
	
	@Test
	public void testdegreesToDirectionVRB() {
		String res = Converter.degreesToDirection("anyString");
		assertEquals(Messages.CONVERTER_VRB, res);
	}
	
	@Test
	public void testdegreesToDirectionN() {
		String res1 = Converter.degreesToDirection("2");
		String res2 = Converter.degreesToDirection("345");
		
		assertEquals(Messages.CONVERTER_N, res1);
		assertEquals(Messages.CONVERTER_N, res2);
	}
	
	@Test
	public void testToDirection() {
		assertEquals(Messages.CONVERTER_E, Converter.degreesToDirection("80"));
		assertEquals(Messages.CONVERTER_NE, Converter.degreesToDirection("30"));
		assertEquals(Messages.CONVERTER_S, Converter.degreesToDirection("200"));
		assertEquals(Messages.CONVERTER_W, Converter.degreesToDirection("280"));
		assertEquals(Messages.CONVERTER_NW, Converter.degreesToDirection("300"));
		assertEquals(Messages.CONVERTER_SE, Converter.degreesToDirection("130"));
		assertEquals(Messages.CONVERTER_SW, Converter.degreesToDirection("230"));
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
		assertEquals(Messages.CONVERTER_U, Converter.convertTrend("U"));
		assertEquals(Messages.CONVERTER_D, Converter.convertTrend("D"));
		assertEquals(Messages.CONVERTER_NSC, Converter.convertTrend("N"));
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
