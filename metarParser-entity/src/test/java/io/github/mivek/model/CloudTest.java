package io.github.mivek.model;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

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
		assertThat(c.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.type") + "=" + CloudType.CB.toString()));
		assertThat(c.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.quantity") + "=" + CloudQuantity.BKN.toString()));
		assertThat(c.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.height.feet") + "=300"));
		assertThat(c.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.height.meter") + "=90"));
	}

	@Test public void testPojo() {
		// given
		final Class<?> classUnderTest = Cloud.class;
		// then
		assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
