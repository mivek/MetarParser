package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class VariableSkyHeightCommand implements Command {
    /** Variable sky condition with height. */
    private static final Pattern VARIABLE_SKY_HEIGHT = Pattern.compile("^([A-Z]{3})(\\d{3}) V ([A-Z]{3})");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    VariableSkyHeightCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] variableSky = Regex.pregMatch(VARIABLE_SKY_HEIGHT, pRemark);
        String layer1 = fMessages.getString("CloudQuantity." + variableSky[1]);
        int height = Integer.parseInt(variableSky[2]) * 100;
        String layer2 = fMessages.getString("CloudQuantity." + variableSky[3]);
        pStringBuilder.append(fMessages.getString("Remark.Variable.Sky.Condition.Height", height, layer1, layer2)).append(" ");
        return pRemark.replaceFirst(VARIABLE_SKY_HEIGHT.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(VARIABLE_SKY_HEIGHT, pInput);
    }
}
