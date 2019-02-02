/**
 * 
 */
package io.github.mivek.model;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Metar;

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
	
	@Test
	public void testGetVerticalVisibility() {
	    //GIVEN a metar object with a null vertical visibility
	    Metar m = new Metar();
	    //WHEN retrieving the vertical visibility
	    Integer result = m.getVerticalVisibility();
	    //THEN the result is null, not a null pointer expection
	    assertNull(result);
	}
}
