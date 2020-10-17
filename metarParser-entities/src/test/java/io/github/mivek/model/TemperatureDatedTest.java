package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TemperatureDatedTest {
    @Test
    public void testToString() {
        TemperatureDated temp = new TemperatureDated();
        temp.setDay(10);
        temp.setHour(15);
        temp.setTemperature(20);

        String des = temp.toString();

        assertThat(des, containsString(Messages.getInstance().getString("ToString.day.month") + "=10"));
        assertThat(des, containsString(Messages.getInstance().getString("ToString.day.hour") + "=15"));
        assertThat(des, containsString(Messages.getInstance().getString("ToString.temperature") + "=20"));
    }
}
