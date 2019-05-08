package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class WindTest {

    @Test
    public void testToString(){
        Wind sut = new Wind();

        sut.setDirection(Messages.getInstance().getString("Converter.NE"));
        sut.setDirectionDegrees(45);
        sut.setSpeed(25);
        sut.setUnit("KM/H");


        String desc = sut.toString();

        assertThat(desc, containsString(Messages.getInstance().getString("ToString.wind.speed") + "=25"));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.wind.direction") + "=" + Messages.getInstance().getString("Converter.NE")));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.wind.direction.degrees") + "=45"));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.wind.gusts") + "=0"));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.wind.min.variation") + "=0"));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.wind.max.variation") + "=0"));
        assertThat(desc, containsString(Messages.getInstance().getString("ToString.wind.unit") + "=KM/H"));
    }

    @Test public void testPojo() {
        // given
        final Class<?> classUnderTest = Wind.class;
        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
