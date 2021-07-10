package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class PrecipitationEndCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new PrecipitationEndCommand();
        StringBuilder sb = new StringBuilder();
        command.execute("RAE45", sb);
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("rain ending at :45"));
    }

    @Test
    public void testExecuteWithDescriptive() {
        Command command = new PrecipitationEndCommand();
        StringBuilder sb = new StringBuilder();
        command.execute("SHRAE45", sb);
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("showers of rain ending at :45"));
    }
}
