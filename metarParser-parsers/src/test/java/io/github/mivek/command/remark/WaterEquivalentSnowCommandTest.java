package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class WaterEquivalentSnowCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new WaterEquivalentSnowCommand();
        StringBuilder sb = new StringBuilder();
        command.execute("933036",sb);
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("water equivalent of 3.6 inches of snow"));
    }
}
