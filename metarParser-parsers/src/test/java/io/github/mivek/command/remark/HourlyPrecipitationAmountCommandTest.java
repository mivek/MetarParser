package io.github.mivek.command.remark;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HourlyPrecipitationAmountCommandTest {


    @Test
    void testExecute() {
        Command command = new HourlyPrecipitationAmountCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("P0009 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("9/100 of an inch of precipitation fell in the last hour"));
    }

    @Test
    void testCanParse() {
        Command command = new HourlyPrecipitationAmountCommand();
        assertTrue(command.canParse("P0009"));
    }
}
