package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureCommandTest {

    private final TemperatureCommand command = new TemperatureCommand();

    @Test
    void testParseNormalTemperature() {
        Metar metar = new Metar();
        command.execute(metar, "25/10");
        assertEquals(25, metar.getTemperature().intValue());
        assertEquals(10, metar.getDewPoint().intValue());
    }

    @Test
    void testParseNegativeTemperature() {
        Metar metar = new Metar();
        command.execute(metar, "M05/M10");
        assertEquals(-5, metar.getTemperature().intValue());
        assertEquals(-10, metar.getDewPoint().intValue());
    }

    @Test
    void testParseMissingTemperature() {
        Metar metar = new Metar();
        command.execute(metar, "////10");
        assertNull(metar.getTemperature());
        assertEquals(10, metar.getDewPoint().intValue());
    }

    @Test
    void testParseMissingDewPoint() {
        Metar metar = new Metar();
        command.execute(metar, "25////");
        assertEquals(25, metar.getTemperature().intValue());
        assertNull(metar.getDewPoint());
    }

    @Test
    void testParseMissingBoth() {
        Metar metar = new Metar();
        command.execute(metar, "///////");
        assertNull(metar.getTemperature());
        assertNull(metar.getDewPoint());
    }

    @Test
    void testCanParseNormal() {
        assertTrue(command.canParse("25/10"));
    }

    @Test
    void testCanParseSolidus() {
        assertTrue(command.canParse("////10"));
        assertTrue(command.canParse("25////"));
        assertTrue(command.canParse("///////"));
    }

    @Test
    void testCanParseNegative() {
        assertTrue(command.canParse("M05/M10"));
    }

    @Test
    void testParseLargeMissingTemperature() {
        Metar metar = new Metar();
        command.execute(metar, "////M05");
        assertNull(metar.getTemperature());
        assertEquals(-5, metar.getDewPoint().intValue());
    }

    @Test
    void testParseNegativeSolidusTemp() {
        Metar metar = new Metar();
        command.execute(metar, "M05////");
        assertEquals(-5, metar.getTemperature().intValue());
        assertNull(metar.getDewPoint());
    }

    @Test
    void testParseZeroTemperature() {
        Metar metar = new Metar();
        command.execute(metar, "00/M05");
        assertEquals(0, metar.getTemperature().intValue());
        assertEquals(-5, metar.getDewPoint().intValue());
    }
}
