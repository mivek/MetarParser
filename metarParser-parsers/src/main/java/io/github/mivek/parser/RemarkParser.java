package io.github.mivek.parser;

import io.github.mivek.command.remark.RemarkCommandSupplier;
import org.apache.commons.lang3.StringUtils;

import java.util.MissingResourceException;

/**
 * @author mivek
 */
public final class RemarkParser {

    /** The instance of the parser. */
    private static final RemarkParser INSTANCE = new RemarkParser();

    /** The command supplier. */
    private final RemarkCommandSupplier supplier;
    /***
     * Private constructor.
     */
    private RemarkParser() {
        supplier = new RemarkCommandSupplier();
    }

    /**
     * @param pRemark the remark to parse.
     * @return the remark string
     */
    public String parse(final String pRemark) {
        String rmk = pRemark;
        StringBuilder sb = new StringBuilder();
        while (StringUtils.isNotEmpty(rmk)) {
            try {
                rmk = supplier.get(rmk).execute(rmk, sb);
            } catch (MissingResourceException e) {
                rmk = supplier.getDefaultCommand().execute(rmk, sb);
            }
        }
        return sb.toString();
    }

    /**
     * @return the instance of the parser.
     */
    public static RemarkParser getInstance() {
        return INSTANCE;
    }
}
