package io.github.mivek.parser;

import io.github.mivek.model.trend.validity.BeginningValidity;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jean-Kevin KPADEY
 */
class FMTrendParserTest {

  @Test
  void testParseBeginningValidity() {
    FMTrendParser parser = new FMTrendParser();
    String validity = "FM061305";
    BeginningValidity res = parser.parseBasicValidity(validity);
    assertThat(res.getStartDay(), is(6));
    assertThat(res.getStartHour(), is(13));
    assertThat(res.getStartMinutes(), is(5));
  }

  @Test
    void testGetInstance() {
        assertNotNull(FMTrendParser.getInstance());
        assertInstanceOf(FMTrendParser.class, FMTrendParser.getInstance());
    }
}
