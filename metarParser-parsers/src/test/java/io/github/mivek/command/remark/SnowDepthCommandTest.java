package io.github.mivek.command.remark;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SnowDepthCommandTest {


    @Test
    void testExecute() {
        Command command = new SnowDepthCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("4/021 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("snow depth of 21 inches"));
    }

    @Test
    void testCanParse() {
        Command command = new SnowDepthCommand();
        assertTrue(command.canParse("4/021"));
    }
}
