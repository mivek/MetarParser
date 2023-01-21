package io.github.mivek.model;

import static org.hamcrest.MatcherAssert.assertThat;

import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositType;
import io.github.mivek.enums.RunwayInfoIndicator;
import io.github.mivek.enums.RunwayInfoTrend;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class RunwayInfoTest {

    @Test
    void testToString() {
        RunwayInfo ri = new RunwayInfo();
        ri.setMinRange(300);
        ri.setName("14R");
        ri.setTrend(RunwayInfoTrend.UPRISING);
        ri.setMaxRange(500);
        ri.setDepositType(DepositType.COMPACTED_SNOW);
        ri.setCoverage(DepositCoverage.FROM_11_TO_25);
        ri.setThickness(Messages.getInstance().getString("DepositThickness.93"));
        ri.setBrakingCapacity(Messages.getInstance().getString("DepositBrakingCapacity.91"));
        ri.setIndicator(RunwayInfoIndicator.LESS_THAN);

        String des = ri.toString();

        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.name") + "=14R"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min") + "=300"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.max") + "=500"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.trend") + "=up rising"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.type") + "=" + Messages.getInstance().getString("DepositType.COMPACTED_SNOW")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.coverage") + "=" + Messages.getInstance().getString("DepositCoverage.FROM_11_TO_25")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.thickness") + "=" + Messages.getInstance().getString("DepositThickness.93")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.deposit.braking") + "=" + Messages.getInstance().getString("DepositBrakingCapacity.91")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.indicator") + "=less than"));
    }

}
