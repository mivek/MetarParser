package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class PrecipitationAmount36HourCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute3Hours() {
        Command command = new PrecipitationAmount36HourCommand();
        StringBuilder sb = new StringBuilder();

        MatcherAssert.assertThat(command.execute("30217", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("2.17 inches of precipitation fell in the last 3 hours"));
    }

    @Test
    public void testExecute6Hours() {
        Command command = new PrecipitationAmount36HourCommand();
        StringBuilder sb = new StringBuilder();

        MatcherAssert.assertThat(command.execute("60217", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("2.17 inches of precipitation fell in the last 6 hours"));
    }

}
