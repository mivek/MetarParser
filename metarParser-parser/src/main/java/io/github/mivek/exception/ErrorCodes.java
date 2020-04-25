package io.github.mivek.exception;

import io.github.mivek.internationalization.Messages;

/**
 * @author mivek
 *
 */
public enum ErrorCodes {
    /** Error for an invalid ICAO. */
    ERROR_CODE_INVALID_ICAO(1, Messages.getInstance().getString("MetarFacade.InvalidIcao")),
    /** Error for an invalid message. */
    ERROR_CODE_INVALID_MESSAGE(2, Messages.getInstance().getString("ErrorCode.InvalidMessage")),
    /** Error code for when the airport is not found. */
    ERROR_CODE_AIRPORT_NOT_FOUND(3, Messages.getInstance().getString("ErrorCode.AirportNotFound"));
    /** The code of the error. */
    private final int fCode;
    /** The message of the error. */
    private final String fMessage;

    /**
     * constructor.
     * @param pCode the code of the error.
     * @param pMessage the message to set.
     */
    ErrorCodes(final int pCode, final String pMessage) {
        fCode = pCode;
        fMessage = pMessage;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return fCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return fMessage;
    }

    @Override
    public String toString() {
        return Messages.getInstance().getString("Error.prefix") + getCode() + " " + getMessage();
    }
}
