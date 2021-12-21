package io.github.mivek.factory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.github.mivek.model.trend.validity.AbstractValidity;
import io.github.mivek.parser.AbstractTAFTrendParser;
import io.github.mivek.parser.FMTrendParser;
import io.github.mivek.parser.ProbTrendParser;
import io.github.mivek.parser.TrendValididyParser;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class TafTrendParserFactoryTest {

  @Test
  void testCreateWithFM() {
    AbstractTAFTrendParser<? extends AbstractValidity> parser = new TafTrendParserFactory().create("FM");
    assertNotNull(parser);
    assertInstanceOf(FMTrendParser.class, parser);
  }

  @Test
  void testCreateWithTE() {
    AbstractTAFTrendParser<? extends AbstractValidity> parser = new TafTrendParserFactory().create("TE");
    assertNotNull(parser);
    assertInstanceOf(ProbTrendParser.class, parser);
  }
  @Test
  void testCreateWithBE() {
    AbstractTAFTrendParser<? extends AbstractValidity> parser = new TafTrendParserFactory().create("BE");
    assertNotNull(parser);
    assertInstanceOf(TrendValididyParser.class, parser);
  }
  @Test
  void testCreateWithIN() {
    AbstractTAFTrendParser<? extends AbstractValidity> parser = new TafTrendParserFactory().create("IN");
    assertNotNull(parser);
    assertInstanceOf(TrendValididyParser.class, parser);
  }
  @Test
  void testCreateWithPR() {
    AbstractTAFTrendParser<? extends AbstractValidity> parser = new TafTrendParserFactory().create("PR");
    assertNotNull(parser);
    assertInstanceOf(ProbTrendParser.class, parser);
  }
  @Test
  void testCreateWithInvalid() {
    AbstractTAFTrendParser<? extends AbstractValidity> parser = new TafTrendParserFactory().create("UN");
    assertNull(parser);
  }

}
