package io.github.mivek.model.trend.validity;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ValidityTest {
    @Test
    public void testToString() {
        Validity sut = new Validity();
        sut.setStartDay(10);
        sut.setStartHour(15);
        sut.setEndDay(12);
        sut.setEndHour(16);

        String desc = sut.toString();

        assertThat(desc, containsString("starting day of the month=10"));
        assertThat(desc, containsString("starting hour of the day=15"));
        assertThat(desc, containsString("end day of the month=12"));
        assertThat(desc, containsString("end hour of the day=16"));
    }
}
