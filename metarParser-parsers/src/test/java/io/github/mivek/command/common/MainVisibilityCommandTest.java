package io.github.mivek.command.common;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainVisibilityCommandTest {

    private final MainVisibilityCommand command = new MainVisibilityCommand();

    @Test
    void testCanParseNormal() {
        assertTrue(command.canParse("9999"));
        assertTrue(command.canParse("0350"));
        assertTrue(command.canParse("5000"));
    }

    @Test
    void testCanParseSolidus() {
        assertTrue(command.canParse("////"));
    }

    @Test
    void testExecuteNormal() {
        Metar metar = new Metar();
        boolean result = command.execute(metar, "9999");
        assertTrue(result);
        assertNotNull(metar.getVisibility());
        assertNotNull(metar.getVisibility().getMainVisibility());
        assertEquals(LengthUnit.METERS, metar.getVisibility().getUnit());
    }

    @Test
    void testExecuteSolidus() {
        Metar metar = new Metar();
        boolean result = command.execute(metar, "////");
        assertTrue(result);
        assertNotNull(metar.getVisibility());
        // When visibility is missing, mainVisibility should not be set
        assertNull(metar.getVisibility().getMainVisibility());
        assertNull(metar.getVisibility().getUnit());
    }
}
