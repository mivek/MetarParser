package io.github.mivek.exception;

/**
 * @author mivek
 */
public class ParseException extends Exception {
    /** serialVersionUID. */
    private static final long serialVersionUID = -9022781702124062628L;
    /** The error code. */
    private final ErrorCodes codes;

    /**
     * Constructor with error code.
     *
     * @param code the error code.
     */
    public ParseException(final ErrorCodes code) {
        super(code.toString());
        this.codes = code;
    }

    /**
     * @return the error code.
     */
    public ErrorCodes getErrorCode() {
        return codes;
    }
}
