package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class HourlyMinimumTemperatureCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecuteWithNegativeTemperature() {
        Command command = new HourlyMinimumTemperatureCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("21001", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("6-hourly minimum temperature of -0.1°C"));
    }

    @Test
    public void testExecuteWithPositiveTemperature() {
        Command command = new HourlyMinimumTemperatureCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("20012", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("6-hourly minimum temperature of 1.2°C"));
    }
}
