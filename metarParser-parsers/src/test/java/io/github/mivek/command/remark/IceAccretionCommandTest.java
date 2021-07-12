package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class IceAccretionCommandTest {

    @Before
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new IceAccretionCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("l1004", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("4/100 of an inch of ice accretion in the past 1 hour(s)"));
    }
}
