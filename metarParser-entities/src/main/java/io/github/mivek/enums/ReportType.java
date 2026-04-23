package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration class for weather code report types.
 *
 * @author mivek
 */
public enum ReportType {
    /** Routine report. */
    METAR,
    /** Special report. */
    SPECI;

    @Override
    public String toString() {
        return Messages.getInstance().getString("ReportType." + name());
    }
}
