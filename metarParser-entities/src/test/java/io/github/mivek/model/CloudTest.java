package io.github.mivek.model;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class CloudTest {

    @Test
    public void testSetHeightGetHeight() {
        Cloud c = new Cloud();
        c.setHeight(300);
        assertEquals(300, c.getHeight());
    }

    @Test
    public void testToString() {
        Cloud c = new Cloud();
        c.setQuantity(CloudQuantity.BKN);
        c.setHeight(300);
        c.setType(CloudType.CB);
        assertThat(c.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.type") + "=" + CloudType.CB.toString()));
        assertThat(c.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.quantity") + "=" + CloudQuantity.BKN.toString()));
        assertThat(c.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.height.feet") + "=300"));
    }

}
