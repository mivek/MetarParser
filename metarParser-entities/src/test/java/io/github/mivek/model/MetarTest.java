package io.github.mivek.model;

class MetarTest extends AbstractWeatherContainerTest<Metar>{

    @Override
    Metar getEntity() {
        return new Metar();
    }
}
