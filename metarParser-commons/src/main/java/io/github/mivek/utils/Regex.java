package io.github.mivek.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex utility class.
 *
 * @author mivek
 */
public final class Regex {

    /**
     * Private constructor.
     */
    private Regex() {
    }

    /**
     * Similar to PHP function preg_match. Search a match between the pattern
     * (regex) and the subject (input) and returns an array of string results.
     *
     * @param pattern The pattern
     * @param input   The subject.
     * @return Array of matches.
     */
    public static String[] pregMatch(final Pattern pattern, final String input) {
        Matcher m = init(pattern, input);

        if (m.find()) {
            int length = m.groupCount() + 1;
            String[] matches = new String[length];
            for (int i = 0; i < length; i++) {
                matches[i] = m.group(i);
            }
            return matches;
        }
        return new String[0];
    }

    /**
     * Tries to match the regex and the input.
     *
     * @param pattern The compiled pattern
     * @param input   the input to test.
     * @return true if the input matches the regex.
     */
    public static boolean find(final Pattern pattern, final String input) {
        Matcher m = init(pattern, input);
        return m.find();
    }

    /**
     * Returns the subsequence captured if the regex and the input matches.
     *
     * @param pattern The compiled pattern.
     * @param input   The input string
     * @return the finding string.
     */
    public static String findString(final Pattern pattern, final String input) {
        Matcher m = init(pattern, input);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    /**
     * Initiates and returns a matcher of the compiled pattern.
     *
     * @param pattern the compiled pattern regex.
     * @param input   the input to test.
     * @return the initiated matcher.
     */
    private static Matcher init(final Pattern pattern, final String input) {
        return pattern.matcher(input);
    }

    /**
     * Checks if the input matches the regex.
     *
     * @param pattern the compiled pattern regex.
     * @param input   the input to test
     * @return true if the input matches the regex.
     */
    public static boolean match(final Pattern pattern, final String input) {
        Matcher m = init(pattern, input);
        return m.matches();
    }
}
