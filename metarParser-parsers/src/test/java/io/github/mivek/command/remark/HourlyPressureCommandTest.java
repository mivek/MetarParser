package io.github.mivek.command.remark;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HourlyPressureCommandTest {


    @Test
    void testExecute(){
        Command command = new HourlyPressureCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("52032 AO1", sb));

        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("steady or unsteady increase of 3.2 hectopascals in the past 3 hours"));
    }

    @Test
    void testCanParse() {
        Command command = new HourlyPressureCommand();
        assertTrue(command.canParse("52032"));
    }
}
