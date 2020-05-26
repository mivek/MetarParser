package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;

import java.util.MissingResourceException;
import java.util.logging.Logger;

/**
 * @author mivek
 */
public final class DefaultCommand implements Command {
    /** The logger instance. */
    private static final Logger LOGGER = Logger.getLogger(DefaultCommand.class.getName());

    /** Message instance. */
    private final Messages messages;

    /**
     * Constructor.
     */
    DefaultCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public boolean canParse(final String pInput) {
        return true;
    }

    /**
     * Handle the default behavior when a remark token is not recognized by regex.
     *
     * @param pRemark        the remark string
     * @param pStringBuilder The string builder containing the parsed message of the remark
     * @return the remark string.
     */
    @Override
    public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String rmk = pRemark;
        String[] strSlit = rmk.split(" ", 2);
        String token = strSlit[0];
        try {
            token = messages.getString("Remark." + token);
        } catch (MissingResourceException e) {
            LOGGER.info("Remark token \"" + token + "\" is unknown.");
        }
        pStringBuilder.append(token).append(" ");
        rmk = strSlit.length == 1 ? "" : strSlit[1];
        return rmk;
    }
}

