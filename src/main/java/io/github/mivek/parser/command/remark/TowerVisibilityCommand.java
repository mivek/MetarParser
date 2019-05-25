package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class TowerVisibilityCommand implements Command {

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public TowerVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] towerVisibilityParts = Regex.pregMatch(RemarkParser.TOWER_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Tower.Visibility", towerVisibilityParts[1])).append(" ");
        return pRemark.replaceFirst(RemarkParser.TOWER_VISIBILITY.pattern(), "").trim();
    }
}
