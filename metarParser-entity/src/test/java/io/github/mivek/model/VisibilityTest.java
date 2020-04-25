package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class VisibilityTest {

    @Test
    public void testToString(){
        Visibility sut = new Visibility();
        sut.setMainVisibility(">10km");
        sut.setMinDirection("SE");
        sut.setMinVisibility(200);

        String des = sut.toString();

        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.main") + "=>10km"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min.direction") + "=SE"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.visibility.min") + "=200"));
    }

    @Test public void testPojo() {
        // given
        final Class<?> classUnderTest = Visibility.class;
        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
