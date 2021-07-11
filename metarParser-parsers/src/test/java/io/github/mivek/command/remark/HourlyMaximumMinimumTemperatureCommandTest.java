package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;

public class HourlyMaximumMinimumTemperatureCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        HourlyMaximumMinimumTemperatureCommand command = new HourlyMaximumMinimumTemperatureCommand();
        StringBuilder sb = new StringBuilder();
        assertThat(command.execute("401001015", sb), Matchers.emptyString());
        assertThat(sb.toString(), CoreMatchers.containsString("24-hour maximum temperature of 10°C and 24-hour minimum temperature of -1.5°C"));
    }
}
