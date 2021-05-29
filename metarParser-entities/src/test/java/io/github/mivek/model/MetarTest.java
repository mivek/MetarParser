package io.github.mivek.model;

import static org.junit.Assert.*;

public class MetarTest extends AbstractWeatherContainerTest<Metar>{

    @Override
    Metar getEntity() {
        return new Metar();
    }
}
