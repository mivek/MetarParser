package io.github.mivek.command.remark;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WaterEquivalentSnowCommandTest {


    @Test
    void testExecute() {
        Command command = new WaterEquivalentSnowCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("933036 AO1",sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("water equivalent of 3.6 inches of snow"));
    }

    @Test
    void testCanParse() {
        Command command = new WaterEquivalentSnowCommand();
        assertTrue(command.canParse("933036"));
    }
}
