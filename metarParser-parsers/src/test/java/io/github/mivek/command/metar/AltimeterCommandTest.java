package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AltimeterCommandTest {

    private final AltimeterCommand command = new AltimeterCommand();

    @ParameterizedTest
    @ValueSource(strings = {"Q1012", "Q0950", "Q////"})
    void testCanParse(final String input) {
        assertTrue(command.canParse(input));
    }

    @Test
    void testExecuteSolidus() {
        Metar metar = new Metar();
        command.execute(metar, "Q////");
        assertNull(metar.getAltimeter());
    }

    @ParameterizedTest
    @CsvSource({"Q1012, 1012", "Q0500, 500", "Q1050, 1050"})
    void testExecute(final String input, final int expected) {
        Metar metar = new Metar();
        command.execute(metar, input);
        assertEquals(Integer.valueOf(expected), metar.getAltimeter());
    }
}
