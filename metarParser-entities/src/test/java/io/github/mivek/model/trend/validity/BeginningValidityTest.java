package io.github.mivek.model.trend.validity;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class BeginningValidityTest {

    @Test
    void testToString() {
        BeginningValidity sut = new BeginningValidity();
        sut.setStartDay(10);
        sut.setStartHour(15);
        sut.setStartMinutes(20);

        String desc = sut.toString();
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.start.day.month") + "=10"));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.start.hour.day") + "=15"));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.start.minute") + "=20"));

    }
}
