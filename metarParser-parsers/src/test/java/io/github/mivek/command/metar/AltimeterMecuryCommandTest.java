package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AltimeterMecuryCommandTest {

    private final AltimeterMecuryCommand command = new AltimeterMecuryCommand();

    @Test
    void testCanParseNormal() {
        assertTrue(command.canParse("A3012"));
        assertTrue(command.canParse("A2990"));
    }

    @Test
    void testCanParseSolidus() {
        assertTrue(command.canParse("A////"));
    }

    @Test
    void testExecuteNormal() {
        Metar metar = new Metar();
        command.execute(metar, "A3012");
        // A3012 = 30.12 inches of mercury, which converts to approximately 1019 hPa
        assertEquals(1019, metar.getAltimeter().intValue());
    }

    @Test
    void testExecuteSolidus() {
        Metar metar = new Metar();
        command.execute(metar, "A////");
        assertNull(metar.getAltimeter());
    }
}
