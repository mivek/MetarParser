package io.github.mivek.model;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class VisibilityTest {

    @Test
    public void testToString(){
        Visibility sut = new Visibility();
        sut.setMainVisibility(">10km");
        sut.setMinDirection("SE");
        sut.setMinVisibility(200);

        String des = sut.toString();

        assertThat(des, Matchers.containsString("main visibility=>10km"));
        assertThat(des, Matchers.containsString("min direction=SE"));
        assertThat(des, Matchers.containsString("min visibility=200"));
    }
}
