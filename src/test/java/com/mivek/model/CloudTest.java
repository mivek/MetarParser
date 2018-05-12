package com.mivek.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;

public class CloudTest {
	@Test
	public void testSetAltitudeGetAltitude() {
		Cloud c = new Cloud();
		c.setAltitude(90);
		assertEquals(90, c.getAltitude());
	}
	
	@Test
	public void testSetAltitudeGetHeight() {
		Cloud c = new Cloud();
		c.setAltitude(90);
		assertEquals(300, c.getHeight());
	}
	
	@Test
	public void testSetHeightGetAltitude() {
		Cloud c = new Cloud();
		c.setHeight(300);
		assertEquals(90, c.getAltitude());
	}

	@Test
	public void testSetHeightGetHeight() {
		Cloud c = new Cloud();
		c.setHeight(300);
		assertEquals(300, c.getHeight());
	}

	@Test
	public void testToString() {
		Cloud c = new Cloud();
		c.setAltitude(90);
		c.setQuantity(CloudQuantity.BKN);
		c.setType(CloudType.CB);
		assertEquals("Broken Cumulonimbus 300ft (approx 90m)", c.toString());
	}
}
