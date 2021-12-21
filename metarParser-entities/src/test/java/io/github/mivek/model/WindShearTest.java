package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class WindShearTest {

    @Test
    void testToString() {
        WindShear sut = new WindShear();
        sut.setHeight(500);

        assertThat(sut.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.height.feet") + "=500"));
    }
}
