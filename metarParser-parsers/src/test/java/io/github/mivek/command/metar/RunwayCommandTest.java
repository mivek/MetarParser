package io.github.mivek.command.metar;

import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositType;
import io.github.mivek.enums.RunwayInfoIndicator;
import io.github.mivek.enums.RunwayInfoTrend;
import io.github.mivek.exception.ParseException;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mivek
 */
class RunwayCommandTest {

    private RunwayCommand command;

    @BeforeEach
    void setUp() {
        command = new RunwayCommand();
    }

    @Test
    void testExecuteSimple() throws ParseException {
        String riString = "R26/0600U";
        Metar m = new Metar();
        command.execute(m, riString);

        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);
        assertNotNull(ri);
        assertEquals("26", ri.getName());
        assertEquals(600, ri.getMinRange());
        assertNull(ri.getMaxRange());
        assertEquals(RunwayInfoTrend.UPRISING, ri.getTrend());
    }

    @Test
    void testParseRunWaysComplex() throws ParseException {
        String riString = "R26L/0550V700U";
        Metar m = new Metar();

        command.execute(m, riString);
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);
        assertNotNull(ri);
        assertEquals("26L", ri.getName());
        assertEquals(550, ri.getMinRange());
        assertEquals(700, ri.getMaxRange());
        assertEquals(RunwayInfoTrend.UPRISING, ri.getTrend());
    }

    @Test
    void testParseRunwayVisualRangeFeetVariable() throws ParseException {
        Metar m = new Metar();

        command.execute(m, "R01L/0600V1000FT");
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo r = m.getRunways().get(0);
        assertEquals("01L", r.getName());
        assertEquals(600, r.getMinRange());
        assertEquals(1000, r.getMaxRange());
        assertNull(r.getTrend());
    }

    @Test
    void testParseRunwayVisualRangeFeetSimple() throws ParseException {
        Metar m = new Metar();

        command.execute(m, "R01L/0800FT");
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo r = m.getRunways().get(0);
        assertEquals("01L", r.getName());
        assertEquals(800, r.getMinRange());
        assertNull(r.getMaxRange());
        assertNull(r.getTrend());
    }

    @Test
    void testParseRunWayNull() throws ParseException {
        String riString = "R26R/AZEZFDFS";

        Metar m = new Metar();

        command.execute(m, riString);
        assertThat(m.getRunways(), hasSize(0));
    }

    @Test
    void testParseRunwayDeposit() throws ParseException {
        Metar m = new Metar();

        command.execute(m, "R05/629294");
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);

        assertEquals("05", ri.getName());
        assertEquals(DepositType.SLUSH, ri.getDepositType());
        assertEquals(DepositCoverage.FROM_11_TO_25, ri.getCoverage());
        assertEquals(Messages.getInstance().getString("DepositThickness.92"), ri.getThickness());
        assertEquals(Messages.getInstance().getString("DepositBrakingCapacity.94"), ri.getBrakingCapacity());
    }

    @Test
    void testParseRunwayDepositWithNotReportedType() throws ParseException {
        Metar m = new Metar();

        command.execute(m, "R05//29294");
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);

        assertEquals("05", ri.getName());
        assertEquals(DepositType.NOT_REPORTED, ri.getDepositType());
        assertEquals(DepositCoverage.FROM_11_TO_25, ri.getCoverage());
        assertEquals(Messages.getInstance().getString("DepositThickness.92"), ri.getThickness());
        assertEquals(Messages.getInstance().getString("DepositBrakingCapacity.94"), ri.getBrakingCapacity());
    }

    @Test
    void testParseRunwayDepositWithInvalidDeposit() throws ParseException {
        Metar m = new Metar();

        command.execute(m, "R05/6292/4");
        assertThat(m.getRunways(), hasSize(0));
    }

    @Test
    void testParseRunwayWithLessThanIndicatorAndUnit() throws ParseException {
        Metar m = new Metar();

        command.execute(m, "R01L/M0600FT");
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);
        assertEquals("01L", ri.getName());
        assertEquals(RunwayInfoIndicator.LESS_THAN, ri.getIndicator());
        assertEquals(600, ri.getMinRange());
    }

    @Test
    void testParseRunwayWithGreaterThanIndicator() throws ParseException {
        Metar m = new Metar();

        command.execute(m, "R01L/P0600FT");
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);
        assertEquals("01L", ri.getName());
        assertEquals(RunwayInfoIndicator.MORE_THAN, ri.getIndicator());
        assertEquals(600, ri.getMinRange());
    }

    @Test
    void testParseRunwayWithIncompleteInfo() {
        Metar m = new Metar();
        ParseException e = assertThrows(ParseException.class, () -> {
            command.execute(m, "R16///////");
        });
        assertEquals(Messages.getInstance().getString("ErrorCode.IncompleteRunwayInformation"), e.getErrorCode().getMessage());
    }
}
