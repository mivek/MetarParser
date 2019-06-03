package io.github.mivek.parser.command.remark;

/**
 * @author mivek
 */
public interface Command {

    /**
     * @param pRemark        the remark to parse.
     * @param pStringBuilder the string builder containing the decoded remark
     * @return the remark without the parsed part
     */
    String execute(String pRemark, StringBuilder pStringBuilder);

    /**
     * Checks if the string is null.
     *
     * @param pString the string to test
     * @return empty string if null pString otherwise.
     */
    default String verifyString(final String pString) {
        if (pString == null) {
            return "";
        }
        return pString;
    }

    /**
     * @param pInput the input string to test.
     * @return true if the input can be handled by the command.
     */
    boolean canParse(String pInput);
}
