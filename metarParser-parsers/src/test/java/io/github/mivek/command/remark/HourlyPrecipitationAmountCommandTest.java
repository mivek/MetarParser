package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HourlyPrecipitationAmountCommandTest {

    @BeforeEach
    void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

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
