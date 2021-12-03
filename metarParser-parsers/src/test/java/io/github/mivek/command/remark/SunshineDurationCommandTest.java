package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SunshineDurationCommandTest {

    @BeforeEach
    void setup() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    void testExecute() {
        Command command = new SunshineDurationCommand();
        StringBuilder sb = new StringBuilder();

        assertEquals("AO1", command.execute("98096 AO1", sb));
        MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("96 minutes of sunshine"));
    }

    @Test
    void testCanParse() {
        Command command = new SunshineDurationCommand();
        assertTrue(command.canParse("98096"));
    }
}
