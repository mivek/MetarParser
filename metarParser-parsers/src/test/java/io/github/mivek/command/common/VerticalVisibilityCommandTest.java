package io.github.mivek.command.common;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.model.Metar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VerticalVisibilityCommandTest {

    private VerticalVisibilityCommand command;

    @BeforeEach
    void setUp() {
        command = new VerticalVisibilityCommand();
    }

    @Test
    void testCanParse() {
        assertTrue(command.canParse("VV002"));
        assertTrue(command.canParse("VV010"));
    }

    @Test
    void testExecuteSetsValueAndUnit() {
        Metar metar = new Metar();
        command.execute(metar, "VV002");

        assertEquals(200, metar.getVerticalVisibility());
        assertEquals(LengthUnit.FEET, metar.getVerticalVisibilityUnit());
    }
}
