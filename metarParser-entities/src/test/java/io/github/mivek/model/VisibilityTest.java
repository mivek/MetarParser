package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VisibilityTest {

    @Test
    void testToString() {
        Visibility sut = new Visibility();
        sut.setMainVisibility(">10000");
        sut.setMinDirection("SE");
        sut.setMinVisibility(200);
        sut.setUnit(LengthUnit.METERS);

        String des = sut.toString();

        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.main") + "=>10000"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min.direction") + "=SE"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min") + "=200"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.unit") + "=M"));
    }

    @Test
    void testUnitIsNullByDefault() {
        Visibility sut = new Visibility();
        assertNull(sut.getUnit());
    }

    @Test
    void testSetGetUnit() {
        Visibility sut = new Visibility();
        sut.setUnit(LengthUnit.METERS);
        assertEquals(LengthUnit.METERS, sut.getUnit());
    }
}
