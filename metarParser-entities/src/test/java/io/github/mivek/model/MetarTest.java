package io.github.mivek.model;

public class MetarTest extends AbstractWeatherContainerTest<Metar>{

    @Override
    Metar getEntity() {
        return new Metar();
    }
}
