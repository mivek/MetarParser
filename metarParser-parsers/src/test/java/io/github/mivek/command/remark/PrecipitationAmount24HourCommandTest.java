package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrecipitationAmount24HourCommandTest {

    @BeforeEach
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new PrecipitationAmount24HourCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("70125 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("1.25 inches of precipitation fell in the last 24 hours"));
    }

    @Test
    public void testCanParse() {
        Command command = new PrecipitationAmount24HourCommand();
        assertTrue(command.canParse("70125"));
    }
}
