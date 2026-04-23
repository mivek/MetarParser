package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AltimeterCommandTest {

    private final AltimeterCommand command = new AltimeterCommand();

    @Test
    void testCanParseNormal() {
        assertTrue(command.canParse("Q1012"));
        assertTrue(command.canParse("Q0950"));
    }

    @Test
    void testCanParseSolidus() {
        assertTrue(command.canParse("Q////"));
    }

    @Test
    void testExecuteNormal() {
        Metar metar = new Metar();
        command.execute(metar, "Q1012");
        assertEquals(1012, metar.getAltimeter().intValue());
    }

    @Test
    void testExecuteSolidus() {
        Metar metar = new Metar();
        command.execute(metar, "Q////");
        assertNull(metar.getAltimeter());
    }

    @Test
    void testParseAltimeterEdgeCases() {
        Metar metar = new Metar();
        command.execute(metar, "Q0500");
        assertEquals(500, metar.getAltimeter().intValue());
    }

    @Test
    void testParseAltimeterMaximum() {
        Metar metar = new Metar();
        command.execute(metar, "Q1050");
        assertEquals(1050, metar.getAltimeter().intValue());
    }
}
