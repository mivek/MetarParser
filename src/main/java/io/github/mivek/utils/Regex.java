package io.github.mivek.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex utility class.
 * @author mivek
 */
public final class Regex {
    /**
     * Matcher (input).
     */
    private static Matcher m;

    /**
     * Private constructor.
     */
    private Regex() {
    }

    /**
     * Similar to PHP function preg_match. Search a match between the pattern
     * (regex) and the subject (input) and returns an array of string results.
     * @param pRegex
     * The pattern
     * @param pInput
     * The subject.
     * @return Array of matches.
     */
    public static String[] pregMatch(final String pRegex, final String pInput) {
        init(pRegex, pInput);

        if (m.matches()) {
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
     * @param pRegex
     * The regex to test.
     * @param pInput
     * the input to test.
     * @return true if the input matches the regex.
     */
    public static boolean find(final String pRegex, final String pInput) {
        init(pRegex, pInput);
        return m.find();
    }

    /**
     * Returns the subsequence captured if the regex and the input matches.
     * @param pRegex
     * The regex to test.
     * @param pInput
     * The input string
     * @return the finding string.
     */
    public static String findString(final String pRegex, final String pInput) {
        if (find(pRegex, pInput)) {
            return m.group(1);
        }
        return null;
    }

    /**
     * Initiate the pattern and the matcher.
     * @param regex
     * The regex.
     * @param input
     * The input to test.
     */
    private static void init(final String regex, final String input) {
        Pattern p = Pattern.compile(regex);
        m = p.matcher(input);
    }

    /**
     * Checks if the input matches the regex.
     * @param pRegex the regex
     * @param pInput the input to test
     * @return true if the input matches the regex.
     */
    public static boolean match(final String pRegex, final String pInput) {
        init(pRegex, pInput);
        return m.matches();
    }
}
