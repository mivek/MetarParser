package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class TowerVisibilityCommand implements Command {
    /** Tower visibility. */
    private static final Pattern TOWER_VISIBILITY = Pattern.compile("^TWR VIS ((\\d)*( )?(\\d?/?\\d))");

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public TowerVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] towerVisibilityParts = Regex.pregMatch(TOWER_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Tower.Visibility", towerVisibilityParts[1])).append(" ");
        return pRemark.replaceFirst(TOWER_VISIBILITY.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(TOWER_VISIBILITY, pInput);
    }
}
