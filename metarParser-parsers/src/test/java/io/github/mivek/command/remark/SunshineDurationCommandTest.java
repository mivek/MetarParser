package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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

        command.execute("98096", sb);

        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("96 minutes of sunshine"));
    }
}
