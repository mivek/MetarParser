package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class VisibilityTest {

    @Test
    void testToString() {
        Visibility sut = new Visibility();
        sut.setMainVisibility(">10km");
        sut.setMinDirection("SE");
        sut.setMinVisibility(200);

        String des = sut.toString();

        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.main") + "=>10km"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min.direction") + "=SE"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min") + "=200"));
    }
}
