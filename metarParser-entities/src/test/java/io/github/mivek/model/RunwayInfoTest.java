package io.github.mivek.model;

import io.github.mivek.enums.DepositBrakingCapacity;
import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositThickness;
import io.github.mivek.enums.DepositType;
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
        ri.setDepositType(DepositType.COMPACTED_SNOW);
        ri.setCoverage(DepositCoverage.FROM_11_TO_25);
        ri.setThickness(DepositThickness.THICKNESS_15);
        ri.setBrakingCapacity(DepositBrakingCapacity.POOR);
        ri.setIndicator("less than");

        String des = ri.toString();

        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.name") + "=14R"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min") + "=300"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.max") + "=500"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.trend") + "=rising"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.type") + "=" + Messages.getInstance().getString("DepositType.COMPACTED_SNOW")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.coverage") + "=" + Messages.getInstance().getString("DepositCoverage.FROM_11_TO_25")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.thickness") + "=" + Messages.getInstance().getString("DepositThickness.THICKNESS_15")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.braking") + "=" + Messages.getInstance().getString("DepositBrakingCapacity.POOR")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.indicator") + "=less than"));
    }

}
