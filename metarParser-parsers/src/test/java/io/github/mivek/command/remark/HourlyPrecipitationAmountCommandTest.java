package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class HourlyPrecipitationAmountCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new HourlyPrecipitationAmountCommand();
        StringBuilder sb = new StringBuilder();
        command.execute("P0009", sb);
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("9/100 of an inch of precipitation fell in the last hour"));
    }
}
