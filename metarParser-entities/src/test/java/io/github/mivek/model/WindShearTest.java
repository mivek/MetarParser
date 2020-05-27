package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class WindShearTest {

    @Test
    public void testToString() {
        WindShear sut = new WindShear();
        sut.setHeight(500);

        assertThat(sut.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.height.feet") + "=500"));
    }

    @Test
    public void testPojo() {
        // given
        final Class<?> classUnderTest = WindShear.class;
        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
