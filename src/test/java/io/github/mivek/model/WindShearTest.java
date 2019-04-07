package io.github.mivek.model;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class WindShearTest {

    @Test
    public void testToString(){
        WindShear sut = new WindShear();
        sut.setHeight(500);

        assertThat(sut.toString(), Matchers.containsString("height (feet)=500"));
    }
}
