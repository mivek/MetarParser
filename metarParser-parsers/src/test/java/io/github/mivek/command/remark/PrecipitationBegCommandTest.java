package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class PrecipitationBegCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new PrecipitationBegCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("RAB45", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("rain beginning at :45"));
    }


    @Test
    public void testExecuteWithDescriptive() {
        Command command = new PrecipitationBegCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("SHRAB45", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("showers of rain beginning at :45"));
    }
}
