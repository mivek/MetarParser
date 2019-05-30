package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class VariableSkyCommand implements Command {
    /** Variable sky condition. */
    private static final Pattern VARIABLE_SKY = Pattern.compile("^([A-Z]{3}) V ([A-Z]{3})");

    /** The messages instance. */
    private Messages fMessages;

    /**
     * Default constructor.
     */
    public VariableSkyCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] variableSky = Regex.pregMatch(VARIABLE_SKY, pRemark);
        String layer1 = fMessages.getString("CloudQuantity." + variableSky[1]);
        String layer2 = fMessages.getString("CloudQuantity." + variableSky[2]);
        pStringBuilder.append(fMessages.getString("Remark.Variable.Sky.Condition", layer1, layer2)).append(" ");
        return pRemark.replaceFirst(VARIABLE_SKY.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(VARIABLE_SKY, pInput);
    }
}

