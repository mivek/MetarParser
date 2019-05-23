package io.github.mivek.parser.remark;

/**
 * @author mivek
 */
public interface Command {

    /**
     * @param pRemark        the remark to parse.
     * @param pStringBuilder the stringbuilder containing the decoded remark
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
}
