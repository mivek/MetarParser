package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Locale;

public class HourlyPressureCommandTest {

    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute(){
        Command command = new HourlyPressureCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("52032", sb), Matchers.emptyString());

        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("steady or unsteady increase of 3.2 hectopascals in the past 3 hours"));
    }
}
