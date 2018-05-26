package com.mivek.exception;

import internationalization.Messages;

/**
 * @author mivek
 *
 */
public enum ErrorCodes {
    /**
     * Error for an invalid ICAO.
     */
    ERROR_CODE_INVALID_ICAO(1, Messages.INVALID_ICAO),
    /**
     * Error for an invalid message.
     */
    ERROR_CODE_INVALID_MESSAGE(2, Messages.INVALID_MESSAGE),
    /**
     * Error code for when the airport is not found.
     */
    ERROR_CODE_AIRPORT_NOT_FOUND(3, Messages.AIRPORT_NOT_FOUND);
    /**
     * The code of the error.
     */
    private int fCode;
    /**
     * The message of the error.
     */
    private String fMessage;

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
        return Messages.ERROR_PREFIX + getCode() + " " + getMessage();
    }
}
