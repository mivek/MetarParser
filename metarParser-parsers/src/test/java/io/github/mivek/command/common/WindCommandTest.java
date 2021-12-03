package io.github.mivek.command.common;

import io.github.mivek.internationalization.Messages;
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

    private WindCommand sut;

    @BeforeEach
    void setUp() {
        sut = new WindCommand();
    }

    @Test
    void testParseWindSimple() {
        String windPart = "34008KT";

        Wind res = sut.parseWind(windPart);

        assertNotNull(res);
        assertThat(res.getDirection(), is(Messages.getInstance().getString("Converter.NNW")));
        assertEquals(Integer.valueOf(340), res.getDirectionDegrees());
        assertEquals(8, res.getSpeed());
        assertEquals(0, res.getGust());
        assertEquals("KT", res.getUnit());

    }

    @Test
    void testParseWindWithGusts() {
        String windPart = "12017G20KT";

        Wind res = sut.parseWind(windPart);

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

        Wind res = sut.parseWind(windPart);

        assertNotNull(res);
        assertEquals(Messages.getInstance().getString("Converter.VRB"), res.getDirection());
        assertEquals(8, res.getSpeed());
        assertNull(res.getDirectionDegrees());
    }
}
