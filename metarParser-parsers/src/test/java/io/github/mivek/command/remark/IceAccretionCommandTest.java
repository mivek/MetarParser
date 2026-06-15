package io.github.mivek.command.remark;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IceAccretionCommandTest {


    @Test
    void testExecute() {
        Command command = new IceAccretionCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("l1004 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("4/100 of an inch of ice accretion in the past 1 hour(s)"));
    }

    @Test
    void testCanParse() {
        Command command = new IceAccretionCommand();
        assertTrue(command.canParse("l1004"));
    }
}
