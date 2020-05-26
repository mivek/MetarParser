package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class SectorVisibilityCommand implements Command {
    /** Sector visibility. */
    private static final Pattern SECTOR_VISIBILITY = Pattern.compile("^VIS ([A-Z]{1,2}) ((\\d)*( )?(\\d?/?\\d))");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constrctor.
     */
    SectorVisibilityCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] sectorVisibilityParts = Regex.pregMatch(SECTOR_VISIBILITY, pRemark);
        pStringBuilder.append(messages.getString("Remark.Sector.Visibility", messages.getString("Converter." + sectorVisibilityParts[1]), sectorVisibilityParts[2])).append(" ");
        return pRemark.replaceFirst(SECTOR_VISIBILITY.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String pInput) {
        return Regex.find(SECTOR_VISIBILITY, pInput);
    }
}
