package io.github.mivek.command.remark;

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
    private final Messages messages;

    /**
     * Default constructor.
     */
    TowerVisibilityCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] towerVisibilityParts = Regex.pregMatch(TOWER_VISIBILITY, remark);
        stringBuilder.append(messages.getString("Remark.Tower.Visibility", towerVisibilityParts[1])).append(" ");
        return remark.replaceFirst(TOWER_VISIBILITY.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(TOWER_VISIBILITY, input);
    }
}
