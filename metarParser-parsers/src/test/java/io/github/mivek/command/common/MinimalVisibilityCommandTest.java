package io.github.mivek.command.common;

import io.github.mivek.model.Metar;
import io.github.mivek.model.Visibility;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimalVisibilityCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"NW", "N","NE","E","SE","S","SW","W"})
    void execute(final String direction) {
        MinimalVisibilityCommand command = new MinimalVisibilityCommand();
        String input = "0600" + direction;
        Metar metar = new Metar();
        metar.setVisibility(new Visibility());
        command.execute(metar, input);
        assertEquals(600, metar.getVisibility().getMinVisibility());
        assertEquals(direction, metar.getVisibility().getMinDirection());
    }
}