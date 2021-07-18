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

public class HourlyMaximumTemperatureCommandTest {

    @BeforeEach
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecuteBelowZero() {
        Command command = new HourlyMaximumTemperatureCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("11021 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("6-hourly maximum temperature of -2.1°C"));
    }

    @Test
    public void testExecuteAboveZero() {
        Command command = new HourlyMaximumTemperatureCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("10142", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("6-hourly maximum temperature of 14.2°C"));
    }

    @Test
    public void testCanParse() {
        Command command = new HourlyMaximumTemperatureCommand();
        assertTrue(command.canParse("10142"));
    }
}
