package io.github.mivek.parser;

import io.github.mivek.model.TAF;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.Wind;

public class GeneralParseTAFTest extends GeneralParseTest<TAF> {

  @Override
  protected TAF getWeatherCode() {
    TAF taf = new TAF();
    taf.setVisibility(new Visibility());
    taf.setWind(new Wind());
    return taf;
  }

  @Override
  protected AbstractParser<TAF> getSut() {
    return TAFParser.getInstance();
  }
}
