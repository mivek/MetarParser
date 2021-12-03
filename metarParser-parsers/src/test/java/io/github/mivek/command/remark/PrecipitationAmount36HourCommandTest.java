package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrecipitationAmount36HourCommandTest {

    @BeforeEach
    void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    void testExecute3Hours() {
        Command command = new PrecipitationAmount36HourCommand();
        StringBuilder sb = new StringBuilder();

        assertEquals("AO1", command.execute("30217 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("2.17 inches of precipitation fell in the last 3 hours"));
    }

    @Test
    void testExecute6Hours() {
        Command command = new PrecipitationAmount36HourCommand();
        StringBuilder sb = new StringBuilder();

        MatcherAssert.assertThat(command.execute("60217", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("2.17 inches of precipitation fell in the last 6 hours"));
    }

    @Test
    void testCanParse() {
        Command command = new PrecipitationAmount36HourCommand();
        assertTrue(command.canParse("60217"));
    }

}
