package io.github.mivek.parser;

import io.github.mivek.command.remark.RemarkCommandSupplier;

import java.util.MissingResourceException;

/**
 * @author mivek
 */
public final class RemarkParser {

    /** The command supplier. */
    private final RemarkCommandSupplier supplier;

    /**
     * Dependency injection constructor.
     * @param supplier the command supplier
     */
    public RemarkParser(final RemarkCommandSupplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Default constructor.
     */
    public RemarkParser() {
        this(new RemarkCommandSupplier());
    }

    /**
     * @param remark the remark to parse.
     * @return the remark string
     */
    public String parse(final String remark) {
        String rmk = remark;
        StringBuilder sb = new StringBuilder();
        while (!rmk.isBlank()) {
            try {
                rmk = supplier.get(rmk).execute(rmk, sb);
            } catch (MissingResourceException e) {
                rmk = supplier.getDefaultCommand().execute(rmk, sb);
            }
        }
        return sb.toString();
    }

    /**
     * @deprecated Use the default constructor instead.
     * @return the instance of the parser.
     */
    @Deprecated(forRemoval = true, since = "2.19.0")
    public static RemarkParser getInstance() {
        return new RemarkParser();
    }
}
