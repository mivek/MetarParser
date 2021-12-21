package io.github.mivek.model;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.enums.Descriptive;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link AbstractWeatherContainer}.
 *
 * @author mivek
 */
@Disabled
abstract class AbstractWeatherContainerTest<T extends AbstractWeatherContainer> {

    abstract T getEntity();

    @Test
    void testAddWeatherConditionWithNull() {
        T container = getEntity();
        container.addWeatherCondition(null);
        assertThat(container.getWeatherConditions(), empty());
    }

    @Test
    void testAddCloudWithNull() {
        T container = getEntity();
        container.addCloud(null);
        assertThat(container.getClouds(), empty());
    }

    @Test
    void testAddCloudWithValidCloud() {
        T container = getEntity();
        Cloud c = new Cloud();
        c.setQuantity(CloudQuantity.BKN);
        c.setType(CloudType.AC);
        c.setHeight(200);
        container.addCloud(c);

        assertThat(container.getClouds(), hasSize(1));
        assertEquals(c, container.getClouds().get(0));
    }

    @Test
    void testGetVerticalVisibility() {
        //GIVEN a metar object with a null vertical visibility
        T container = getEntity();
        //WHEN retrieving the vertical visibility
        Integer result = container.getVerticalVisibility();
        //THEN the result is null, not a null pointer exception
        assertNull(result);
    }

    @Test
    void testAddWeatherCondition() {
        T container = getEntity();
        WeatherCondition wc = new WeatherCondition();

        wc.setDescriptive(Descriptive.THUNDERSTORM);

        assertTrue(container.addWeatherCondition(wc));
        assertThat(container.getWeatherConditions(), hasSize(1));
    }
}
