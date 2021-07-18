package io.github.mivek.utils;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegexTest {

    @Test
    public void testPregMatchSuccess() {
        Pattern regex = Pattern.compile("(a((b)(c)))");
        String input = "abc";

        String[] res = Regex.pregMatch(regex, input);

        assertThat(res, is(not(emptyArray())));
        assertThat(res, arrayWithSize(5));
        assertThat(res, arrayContainingInAnyOrder("abc", "abc", "bc", "b", "c"));
    }

    @Test
    public void testPregMatchFail() {
        Pattern regex = Pattern.compile("(a((b)(c)))");
        String input = "";

        String[] res = Regex.pregMatch(regex, input);

        assertThat(res, is(emptyArray()));
    }

    @Test
    public void testFind() {
        Pattern regex = Pattern.compile("^(R\\d{2}\\w?/)");
        String input1 = "R26/0550V700U";
        String input2 = "Random string";

        assertTrue(Regex.find(regex, input1));
        assertFalse(Regex.find(regex, input2));
    }

    @Test
    public void testMatch() {
        Pattern regex = Pattern.compile("(VRB|\\d{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM/H)?");

        assertTrue(Regex.match(regex, "12012MPS"));
    }

    @Test
    public void testFindString() {
        Pattern regex = Pattern.compile("(TS)");

        assertEquals("TS", Regex.findString(regex, "TSRA"));
        assertNull(Regex.findString(regex, "SHRA"));
    }
}
