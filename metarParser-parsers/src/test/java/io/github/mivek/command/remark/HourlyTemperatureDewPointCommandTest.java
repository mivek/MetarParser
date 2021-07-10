package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;


public class HourlyTemperatureDewPointCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new HourlyTemperatureDewPointCommand();
        StringBuilder sb = new StringBuilder();
        command.execute("T00261015", sb);
        assertThat(sb.toString(), Matchers.containsString("hourly temperature of 2.6°C and dew point of -1.5°C"));
    }

    @Test
    public void testExecuteOnlyTemperature() {
        Command command = new HourlyTemperatureDewPointCommand();
        StringBuilder sb = new StringBuilder();
        command.execute("T0026", sb);
        assertThat(sb.toString(), Matchers.containsString("hourly temperature of 2.6°C"));
        assertThat(sb.toString(), Matchers.not(Matchers.containsString("dew point of")));
    }


}
