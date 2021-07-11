package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class SunshineDurationCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new SunshineDurationCommand();
        StringBuilder sb = new StringBuilder();

        MatcherAssert.assertThat(command.execute("98096", sb), Matchers.emptyString());

        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("96 minutes of sunshine"));
    }
}
