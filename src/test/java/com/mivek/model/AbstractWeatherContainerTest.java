/**
 * 
 */
package com.mivek.model;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Test class for {@link AbstractWeatherContainer}.
 * @author mivek
 */
public class AbstractWeatherContainerTest {

	@Test
	public void testAddWeatherConditionWithNull() {
		Metar m = new Metar();
		m.addWeatherCondition(null);
		assertThat(m.getWeatherConditions(), empty());
	}

	@Test
	public void testAddCloudWithNull() {
		Metar m = new Metar();
		m.addCloud(null);
		assertThat(m.getClouds(), empty());
	}
}
