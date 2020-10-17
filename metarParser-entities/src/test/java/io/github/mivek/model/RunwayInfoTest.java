package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class RunwayInfoTest {

    @Test
    public void testToString() {
        RunwayInfo ri = new RunwayInfo();
        ri.setMinRange(300);
        ri.setName("14R");
        ri.setTrend("rising");
        ri.setMaxRange(500);

        String des = ri.toString();

        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.name") + "=14R"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min") + "=300"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.max") + "=500"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.trend") + "=rising"));
    }

}
