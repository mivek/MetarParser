package io.github.mivek.model;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class TemperatureDatedTest {
    @Test
    public void testToString() {
        TemperatureDated temp = new TemperatureDated();
        temp.setDay(10);
        temp.setHour(15);
        temp.setTemperature(20);

        String des = temp.toString();

        assertThat(des, containsString("day of the month=10"));
        assertThat(des, containsString("hour of the day=15"));
        assertThat(des, containsString("temperature (°C)=20"));
    }
}
