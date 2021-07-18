package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IceAccretionCommandTest {

    @BeforeEach
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new IceAccretionCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("l1004 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("4/100 of an inch of ice accretion in the past 1 hour(s)"));
    }

    @Test
    public void testCanParse() {
        Command command = new IceAccretionCommand();
        assertTrue(command.canParse("l1004"));
    }
}
