package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class SeaLevelPressureCommand implements Command {

    /** The message instance. */
    private Messages fMessages;

    /**
     * Default constructor.
     */
    public SeaLevelPressureCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] sealevelParts = Regex.pregMatch(RemarkParser.SEAL_LEVEL_PRESSURE, pRemark);
        StringBuilder pressure = new StringBuilder();
        if (sealevelParts[1].startsWith("9")) {
            pressure.append("9");
        } else {
            pressure.append("10");
        }
        pressure.append(sealevelParts[1]).append(".").append(sealevelParts[2]);
        pStringBuilder.append(fMessages.getString("Remark.Sea.Level.Pressure", pressure)).append(" ");
        return pRemark.replaceFirst(RemarkParser.SEAL_LEVEL_PRESSURE.pattern(), "").trim();
    }
}
