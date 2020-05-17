package io.github.mivek.exception;

/**
 * @author mivek
 *
 */
public class ParseException extends Exception {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -9022781702124062628L;
    /**
     * The error code.
     */
    private final ErrorCodes fCode;

    /**
     * Constructor with error code.
     * @param pCode the error code.
     */
    public ParseException(final ErrorCodes pCode) {
        super(pCode.toString());
        fCode = pCode;
    }

    /**
     * @return the error code.
     */
    public ErrorCodes getErrorCode() {
        return fCode;
    }
}
