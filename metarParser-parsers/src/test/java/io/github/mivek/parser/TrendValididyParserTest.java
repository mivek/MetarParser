package io.github.mivek.parser;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.model.trend.TafTrend;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jean-Kevin KPADEY
 */
class TrendValididyParserTest {

  @Test
  void testInvalidTrend() {
    String[] code  = {"TEMPO", "1420/1423", "4000", "BR", "BKN004", "PROB40"};

    TrendValididyParser parser = new TrendValididyParser();

    assertThrows(IllegalArgumentException.class, () -> parser.parse(code));
  }

  @Test
  void testParseTrend() {
    String[] code  = {"BECMG", "1420/1423", "4000", "BR", "BKN004"};

    TrendValididyParser parser = new TrendValididyParser();
    TafTrend trend = parser.parse(code);
    assertNotNull(trend);
    assertEquals(14, trend.getValidity().getStartDay());
    assertEquals(20, trend.getValidity().getStartHour());
    assertEquals(14, trend.getValidity().getEndDay());
    assertEquals(23, trend.getValidity().getEndHour());
    assertEquals("4000m", trend.getVisibility().getMainVisibility());
    MatcherAssert.assertThat(trend.getWeatherConditions(), hasSize(1));
    MatcherAssert.assertThat(trend.getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
    assertEquals(Phenomenon.MIST, trend.getWeatherConditions().get(0).getPhenomenons().get(0));
    MatcherAssert.assertThat(trend.getClouds(), hasSize(1));
    assertEquals(CloudQuantity.BKN, trend.getClouds().get(0).getQuantity());
    assertEquals(400, trend.getClouds().get(0).getHeight());
  }

  @Test
    void testGetInstance() {
        assertNotNull(TrendValididyParser.getInstance());
        assertInstanceOf(TrendValididyParser.class, TrendValididyParser.getInstance());
    }

}
