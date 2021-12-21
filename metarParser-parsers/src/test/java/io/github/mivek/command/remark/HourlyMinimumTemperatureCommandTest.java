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

class HourlyMinimumTemperatureCommandTest {

    @BeforeEach
    void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    void testExecuteWithNegativeTemperature() {
        Command command = new HourlyMinimumTemperatureCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("21001 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("6-hourly minimum temperature of -0.1°C"));
    }

    @Test
    void testExecuteWithPositiveTemperature() {
        Command command = new HourlyMinimumTemperatureCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("20012", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("6-hourly minimum temperature of 1.2°C"));
    }

    @Test
    void testCanParse() {
        Command command = new HourlyMinimumTemperatureCommand();
        assertTrue(command.canParse("20012"));
    }
}
