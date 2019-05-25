package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class SectorVisibilityCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constrctor.
     */
    public SectorVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] sectorVisibilityParts = Regex.pregMatch(RemarkParser.SECTOR_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Sector.Visibility", fMessages.getString(RemarkParser.CONVERTER + sectorVisibilityParts[1]), sectorVisibilityParts[2])).append(" ");
        return pRemark.replaceFirst(RemarkParser.SECTOR_VISIBILITY.pattern(), "").trim();
    }
}
