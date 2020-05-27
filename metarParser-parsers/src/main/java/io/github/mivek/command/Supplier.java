package io.github.mivek.command;

/**
 * @param <T> type of command to return.
 * @author mivek
 */
@FunctionalInterface
public interface Supplier<T> {

    /**
     * @param string the string to parse.
     * @return the command able to parse the string.
     */
    T get(String string);
}

