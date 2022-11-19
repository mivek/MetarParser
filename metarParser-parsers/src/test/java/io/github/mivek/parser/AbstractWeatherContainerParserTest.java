package io.github.mivek.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.WeatherCondition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
@Disabled
abstract class AbstractWeatherContainerParserTest<T extends AbstractWeatherContainer, U> {
  protected abstract AbstractWeatherContainerParser<T, U> getParser();
  /*
   * =================== WEATHER CONDITION ===================
   */
  @Test
  void testParseWCSimple() {
    String wcPart = "-DZ";

    WeatherCondition wc = getParser().parseWeatherCondition(wcPart);

    assertEquals(Intensity.LIGHT, wc.getIntensity());
    assertNull(wc.getDescriptive());

    assertThat(wc.getPhenomenons(), hasSize(1));
    assertThat(wc.getPhenomenons(), hasItem(Phenomenon.DRIZZLE));
  }

  @Test
  void testParseWCMultiplePHE() {
    String wcPart = "SHRAGR";

    WeatherCondition wc = getParser().parseWeatherCondition(wcPart);

    assertNull(wc.getIntensity());
    assertNotNull(wc.getDescriptive());
    assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
    assertThat(wc.getPhenomenons(), hasSize(2));
    assertThat(wc.getPhenomenons(), hasItems(Phenomenon.RAIN, Phenomenon.HAIL));
  }

  @Test
  void testParseWCNull() {
    String wcPart = "-SH";

    WeatherCondition wc = getParser().parseWeatherCondition(wcPart);

    assertNull(wc);
  }

  @Test
  void testParseWCDescriptiveIsNotNullButPhenomenonCanBeEmptyAndIntensityCanBeNull() {
    String wcPart = "SH";

    WeatherCondition wc = getParser().parseWeatherCondition(wcPart);

    assertNull(wc);
  }

  @Test
  void testParseWeatherConditionOrder() {
    WeatherCondition wc = getParser().parseWeatherCondition("-SNRA");

    assertNotNull(wc);
    assertEquals(Intensity.LIGHT, wc.getIntensity());
    assertNull(wc.getDescriptive());
    assertThat(wc.getPhenomenons(), hasSize(2));
    assertEquals(Phenomenon.SNOW, wc.getPhenomenons().get(0));
    assertEquals(Phenomenon.RAIN, wc.getPhenomenons().get(1));
  }
}
