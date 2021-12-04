package io.github.mivek.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class WeatherConditionTest {

  @Test
  public void testIsValidWithVCSH() {
    WeatherCondition wc = new WeatherCondition();
    wc.setIntensity(Intensity.IN_VICINITY);
    wc.setDescriptive(Descriptive.SHOWERS);

    assertTrue(wc.isValid());
  }

  @Test
  public void testIdValidWithVCBL() {
    WeatherCondition wc = new WeatherCondition();
    wc.setIntensity(Intensity.IN_VICINITY);
    wc.setDescriptive(Descriptive.BLOWING);

    assertFalse(wc.isValid());
  }

  @Test
  public void testIsValidWithoutPhenomenons() {
    WeatherCondition wc = new WeatherCondition();
    wc.setIntensity(Intensity.LIGHT);
    wc.setDescriptive(Descriptive.BLOWING);

    assertFalse(wc.isValid());
  }

  @Test
  public void testIsValid() {
    WeatherCondition wc = new WeatherCondition();
    wc.addPhenomenon(Phenomenon.HAIL);

    assertTrue(wc.isValid());
  }

  @Test
  public void testIsValidTS() {
    WeatherCondition wc = new WeatherCondition();
    wc.setDescriptive(Descriptive.THUNDERSTORM);
    assertTrue(wc.isValid());
  }

  @Test
  public void testToString() {
    WeatherCondition sut = new WeatherCondition();
    sut.setIntensity(Intensity.LIGHT);
    sut.setDescriptive(Descriptive.SHOWERS);
    sut.addPhenomenon(Phenomenon.RAIN);

    String desc = sut.toString();

    assertThat(
        desc,
        Matchers.containsString(
            Messages.getInstance().getString("ToString.intensity") + "=" + Intensity.LIGHT));
    assertThat(
        desc,
        Matchers.containsString(
            Messages.getInstance().getString("ToString.descriptive") + "=" + Descriptive.SHOWERS));
    assertThat(desc, Matchers.containsString(Phenomenon.RAIN.toString()));
  }
}
