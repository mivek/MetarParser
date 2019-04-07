package io.github.mivek.model.trend.validity;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class BeginningValidityTest {

    @Test
    public void testToString() {
        BeginningValidity sut = new BeginningValidity();
        sut.setStartDay(10);
        sut.setStartHour(15);
        sut.setStartMinutes(20);

        String desc = sut.toString();
        assertThat(desc, containsString("starting day of the month=10"));
        assertThat(desc, containsString("starting hour of the day=15"));
        assertThat(desc, containsString("starting minute=20"));

    }
}
