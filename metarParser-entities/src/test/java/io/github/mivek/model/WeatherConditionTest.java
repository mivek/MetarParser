package io.github.mivek.model;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class WeatherConditionTest {

    @Test
    public void testIsValid_VCSH() {
        WeatherCondition wc = new WeatherCondition();
        wc.setIntensity(Intensity.IN_VICINITY);
        wc.setDescriptive(Descriptive.SHOWERS);

        assertTrue(wc.isValid());
    }

    @Test
    public void testToString() {
        WeatherCondition sut = new WeatherCondition();
        sut.setIntensity(Intensity.LIGHT);
        sut.setDescriptive(Descriptive.SHOWERS);
        sut.addPhenomenon(Phenomenon.RAIN);

        String desc = sut.toString();

        assertThat(desc, Matchers.containsString(Messages.getInstance().getString("ToString.intensity") + "=" + Intensity.LIGHT.toString()));
        assertThat(desc, Matchers.containsString(Messages.getInstance().getString("ToString.descriptive") + "=" + Descriptive.SHOWERS.toString()));
        assertThat(desc, Matchers.containsString(Phenomenon.RAIN.toString()));
    }
}
