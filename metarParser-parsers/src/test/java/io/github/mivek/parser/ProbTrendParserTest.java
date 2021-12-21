package io.github.mivek.parser;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.TafProbTrend;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class ProbTrendParserTest {

  @Test
  void testParseWithTempoWithoutPROB() {
    String[] code = {"TEMPO", "1707/1710", "2000", "BR", "OVC002"};
    TafProbTrend trend = ProbTrendParser.getInstance().parse(code);

    assertNotNull(trend);
    assertEquals(WeatherChangeType.TEMPO, trend.getType());
    assertNull(trend.getProbability());
    assertEquals(17, trend.getValidity().getStartDay());
    assertEquals(7, trend.getValidity().getStartHour());
    assertEquals(17, trend.getValidity().getEndDay());
    assertEquals(10, trend.getValidity().getEndHour());
    assertEquals("2000m", trend.getVisibility().getMainVisibility());
    MatcherAssert.assertThat(trend.getWeatherConditions(), hasSize(1));
    MatcherAssert.assertThat(trend.getClouds(), hasSize(1));
  }

  @Test
  void testParseWithTempoWithProb() {
    String[] code = {"PROB30", "TEMPO", "1623/1705", "BKN003" };
    TafProbTrend trend = ProbTrendParser.getInstance().parse(code);
    assertNotNull(trend);
    assertEquals(WeatherChangeType.TEMPO, trend.getType());
    assertEquals(30, trend.getProbability());
    assertEquals(16, trend.getValidity().getStartDay());
    assertEquals(23, trend.getValidity().getStartHour());
    assertEquals(17, trend.getValidity().getEndDay());
    assertEquals(5, trend.getValidity().getEndHour());
    MatcherAssert.assertThat(trend.getClouds(), hasSize(1));
  }

  @Test
  void testParseWithProb() {
    String[] code = {"PROB30", "1623/1705", "BKN003" };
    TafProbTrend trend = ProbTrendParser.getInstance().parse(code);
    assertNotNull(trend);
    assertEquals(WeatherChangeType.PROB, trend.getType());
    assertEquals(30, trend.getProbability());
    assertEquals(16, trend.getValidity().getStartDay());
    assertEquals(23, trend.getValidity().getStartHour());
    assertEquals(17, trend.getValidity().getEndDay());
    assertEquals(5, trend.getValidity().getEndHour());
    MatcherAssert.assertThat(trend.getClouds(), hasSize(1));
  }
}
