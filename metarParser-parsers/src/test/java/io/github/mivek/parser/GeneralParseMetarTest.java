package io.github.mivek.parser;

import io.github.mivek.model.Metar;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.Wind;
import io.github.mivek.model.WindShear;

class GeneralParseMetarTest extends GeneralParseTest<Metar> {

    @Override
    protected Metar getWeatherCode() {
        Metar m = new Metar();
        m.setWind(new Wind());
        m.setVisibility(new Visibility());
        m.setWindShear(new WindShear());
        return m;
    }

    @Override
    protected MetarParser getParser() {
        return new MetarParser();
    }

}
