package io.github.mivek.command.common;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.model.Metar;
import io.github.mivek.model.WindShear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WindShearCommandTest {

    private WindShearCommand command;

    @BeforeEach
    void setUp() {
        command = new WindShearCommand();
    }

    @Test
    void testCanParse() {
        assertTrue(command.canParse("WS020/18040KT"));
    }

    @Test
    void testParseWindShearSetsHeightAndUnit() {
        WindShear windShear = command.parseWindShear("WS020/18040KT");

        assertEquals(2000, windShear.getHeight());
        assertEquals(LengthUnit.FEET, windShear.getHeightUnit());
    }

    @Test
    void testExecuteSetsWindShearOnContainer() {
        Metar metar = new Metar();
        command.execute(metar, "WS020/18040KT");

        assertEquals(2000, metar.getWindShear().getHeight());
        assertEquals(LengthUnit.FEET, metar.getWindShear().getHeightUnit());
    }
}
