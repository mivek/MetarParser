package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class SecondLocationVisibilityCommand implements Command {

    /** Visibility at second location. */
    private static final Pattern SECOND_LOCATION_VISIBILITY = Pattern.compile("^VIS ((\\d)*( )?(\\d?/?\\d)) (\\w+)");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SecondLocationVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] secondLocationVisibilityParts = Regex.pregMatch(SECOND_LOCATION_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Second.Location.Visibility", secondLocationVisibilityParts[1], secondLocationVisibilityParts[5])).append(" ");
        return pRemark.replaceFirst(SECOND_LOCATION_VISIBILITY.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(SECOND_LOCATION_VISIBILITY, pInput);
    }
}
