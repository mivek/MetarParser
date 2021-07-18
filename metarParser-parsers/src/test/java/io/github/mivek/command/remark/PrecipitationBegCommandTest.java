package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrecipitationBegCommandTest {

    @BeforeEach
    public void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testExecute() {
        Command command = new PrecipitationBegCommand();
        StringBuilder sb = new StringBuilder();
        assertEquals("AO1", command.execute("RAB45 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("rain beginning at :45"));
    }


    @Test
    public void testExecuteWithDescriptive() {
        Command command = new PrecipitationBegCommand();
        StringBuilder sb = new StringBuilder();
        MatcherAssert.assertThat(command.execute("SHRAB45", sb), Matchers.emptyString());
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("showers of rain beginning at :45"));
    }

    @Test
    public void testCanParse() {
        Command command = new PrecipitationBegCommand();
        assertTrue(command.canParse("SHRAB45"));
    }
}
