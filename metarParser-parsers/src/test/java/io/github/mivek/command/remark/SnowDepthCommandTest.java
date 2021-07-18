package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SnowDepthCommandTest {

    @BeforeEach
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new SnowDepthCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("4/021 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("snow depth of 21 inches"));
    }

    @Test
    public void testCanParse() {
        Command command = new SnowDepthCommand();
        assertTrue(command.canParse("4/021"));
    }
}
