package io.github.mivek.command.common;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Metar;
import io.github.mivek.model.Wind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mivek
 */
class WindCommandTest {

    private WindCommand command;

    @BeforeEach
    void setUp() {
        command = new WindCommand();
    }

    @Test
    void testParseWindSimple() {
        String windPart = "34008KT";

        Wind res = command.parseWind(windPart);

        assertNotNull(res);
        assertThat(res.getDirection(), is(Messages.getInstance().getString("Converter.NNW")));
        assertEquals(Integer.valueOf(340), res.getDirectionDegrees());
        assertEquals(8, res.getSpeed());
        assertNull(res.getGust());
        assertEquals("KT", res.getUnit());

    }

    @Test
    void testParseWindWithGusts() {
        String windPart = "12017G20KT";

        Wind res = command.parseWind(windPart);

        assertNotNull(res);
        assertThat(res.getDirection(), is(Messages.getInstance().getString("Converter.ESE")));
        assertEquals(Integer.valueOf(120), res.getDirectionDegrees());
        assertEquals(17, res.getSpeed());
        assertEquals(20, res.getGust());
        assertEquals("KT", res.getUnit());
    }

    @Test
    void testParseWindVariable() {
        String windPart = "VRB08KT";

        Wind res = command.parseWind(windPart);

        assertNotNull(res);
        assertEquals(Messages.getInstance().getString("Converter.VRB"), res.getDirection());
        assertEquals(8, res.getSpeed());
        assertNull(res.getDirectionDegrees());
    }

    @Test
    void testExecute() {
        String windPart = "VRB08KT";
        Metar m = new Metar();

        assertTrue(command.execute(m, windPart));
    }

    @Test
    void testExecuteWithExistingWind() {
        Wind wind1 = new Wind();
        wind1.setDirection("N");
        wind1.setSpeed(5);
        wind1.setUnit("KT");

        Metar m = new Metar();
        m.setWind(wind1);

        assertTrue(command.execute(m, "VRB08KT"));
        assertEquals(wind1, m.getWind());
    }
}
