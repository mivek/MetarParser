package io.github.mivek.parser.command;

/**
 * @param <T> type of command to return.
 * @author mivek
 */
@FunctionalInterface public interface CommandSupplier<T> {

    /**
     * @param pString the string to parse.
     * @return the command able to parse the string.
     */
    T get(String pString);
}

