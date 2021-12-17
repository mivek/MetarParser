package io.github.mivek.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.mivek.model.trend.validity.BeginningValidity;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class FMTrendParserTest {

  @Test
  void testParseBeginningValidity() {
    FMTrendParser parser = FMTrendParser.getInstance();
    String validity = "FM061305";
    BeginningValidity res = parser.parseBasicValidity(validity);
    assertThat(res.getStartDay(), is(6));
    assertThat(res.getStartHour(), is(13));
    assertThat(res.getStartMinutes(), is(5));
  }
}
