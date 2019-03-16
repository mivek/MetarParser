package io.github.mivek.parser;

import java.text.MessageFormat;
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
    /**Wind peak pattern.*/
    private static final Pattern WIND_PEAK = Pattern.compile("^PK WND (\\d{3})(\\d{2,3})\\/(\\d{2})?(\\d{2})");

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
                sb.append(Messages.getInstance().getString("Remark.AO1")).append(" ");
                rmk = rmk.replaceAll(AO1.pattern(), "").trim();
            } else if (Regex.find(AO2, rmk)) {
                sb.append(Messages.getInstance().getString("Remark.AO2")).append(" ");
                rmk = rmk.replaceAll(AO2.pattern(), "").trim();
            } else if (Regex.find(WIND_PEAK, rmk)) {
                String[] elmts = Regex.pregMatch(WIND_PEAK, rmk);
                sb.append(MessageFormat.format(Messages.getInstance().getString("Remark.PeakWind"), elmts[1], elmts[2], elmts[3] == null ? "" : elmts[3], elmts[4]));
                rmk = rmk.replaceAll(WIND_PEAK.pattern(), "").trim();
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
