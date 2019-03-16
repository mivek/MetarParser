package io.github.mivek.parser;

import java.util.regex.Pattern;

import io.github.mivek.utils.Regex;
import io.github.mivekinternationalization.Messages;

/**
 * @author mivek
 */
public final class RemarkParser {
    /** The instance of the parser. */
    private static final RemarkParser INSTANCE = new RemarkParser();

    /** No precipitation automated stations. */
    private static final Pattern AO1 = Pattern.compile("^AO1");
    /** Automated station with precipitation. */
    private static final Pattern AO2 = Pattern.compile("^AO2");

    /***
     * Private constructor.
     */
    private RemarkParser() {
    }

    /**
     * @param pRemark the remark to parse.
     * @return the remark string
     */
    public String parse(final String pRemark) {
        String rmk = pRemark;
        StringBuilder sb = new StringBuilder();
        while (!rmk.equals("")) {
            if (Regex.find(AO1, rmk)) {
                sb.append(Messages.getInstance().getString("Remark.AO1"));
                rmk = rmk.replaceAll(AO1.pattern(), "").trim();
            } else if (Regex.find(AO2, rmk)) {
                sb.append(Messages.getInstance().getString("Remark.AO2"));
                rmk = rmk.replaceAll(AO2.pattern(), "").trim();
            } else {
                String[] strSlit = rmk.split(" ", 2);
                sb.append(strSlit[0]);
                rmk = strSlit.length == 1 ? "" : strSlit[1];
            }
        }
        return sb.toString();
    }


    /**
     * @return the instance of the parser.
     */
    public static RemarkParser getInstance() {
        return INSTANCE;
    }
}
