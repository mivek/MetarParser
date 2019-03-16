package io.github.mivek.parser;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import io.github.mivekinternationalization.Messages;

public class RemarkParserTest {
    RemarkParser fSut;

    @Before
    public void setUp() {
        fSut = RemarkParser.getInstance();
    }

    @Test
    public void testParseWithAO1() {
        // GIVEN a RMK with AO1 token.
        String code = "Token AO1 End of remark";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.AO1")));
    }

    @Test
    public void testParseWithAO2() {
        // GIVEN a RMK with AO2 token
        String code = "Token AO2 End of remark";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.AO2")));
    }

    @Test
    public void testParseWithWindPeakAtTheHour() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
        // GIVEN a RMK with Peak wind at the hour.
        String code = "AO1 PK WND 28045/15";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString("peak wind of 45 knots from 280 degrees at :15"));
    }

    @Test
    public void testParseWithWindPeakAtAnotherHour() {
        Messages.getInstance().setLocale(Locale.ENGLISH);
        // GIVEN a RMK with Peak wind at the hour.
        String code = "AO1 PK WND 28045/1515";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString("peak wind of 45 knots from 280 degrees at 15:15"));
    }
}
