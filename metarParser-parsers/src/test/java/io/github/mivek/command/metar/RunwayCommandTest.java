package io.github.mivek.command.metar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mivek.enums.DepositBrakingCapacity;
import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositThickness;
import io.github.mivek.enums.DepositType;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class RunwayCommandTest {

  private RunwayCommand command;

  @BeforeEach
  public void setUp() {
    command = new RunwayCommand();
  }

  @Test
  public void testExecuteSimple() {
    String riString = "R26/0600U";
    Metar m = new Metar();
    command.execute(m, riString);

    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo ri = m.getRunways().get(0);
    assertNotNull(ri);
    assertEquals("26", ri.getName());
    assertEquals(600, ri.getMinRange());
    assertEquals(Messages.getInstance().getString("Converter.U"), ri.getTrend());
  }

  @Test
  public void testParseRunWaysComplex() {
    String riString = "R26L/0550V700U";
    Metar m = new Metar();

    command.execute(m, riString);
    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo ri = m.getRunways().get(0);
    assertNotNull(ri);
    assertEquals("26L", ri.getName());
    assertEquals(550, ri.getMinRange());
    assertEquals(700, ri.getMaxRange());
    assertEquals(Messages.getInstance().getString("Converter.U"), ri.getTrend());
  }

  @Test
  public void testParseRunwayVisualRangeFeetVariable() {
    Metar m = new Metar();

    command.execute(m, "R01L/0600V1000FT");
    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo r = m.getRunways().get(0);
    assertEquals("01L", r.getName());
    assertEquals(600, r.getMinRange());
    assertEquals(1000, r.getMaxRange());
    assertThat(r.getTrend(), emptyString());
  }

  @Test
  public void testParseRunwayVisualRangeFeetSimple() {
    Metar m = new Metar();

    command.execute(m, "R01L/0800FT");
    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo r = m.getRunways().get(0);
    assertEquals("01L", r.getName());
    assertEquals(800, r.getMinRange());
    assertThat(r.getTrend(), emptyString());
  }

  @Test
  public void testParseRunWayNull() {
    String riString = "R26R/AZEZFDFS";

    Metar m = new Metar();

    command.execute(m, riString);
    assertThat(m.getRunways(), hasSize(0));
  }

  @Test
  public void testParseRunwayDeposit() {
    Metar m = new Metar();

    command.execute(m, "R05/629294");
    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo ri = m.getRunways().get(0);

    assertEquals("05", ri.getName());
    assertEquals(DepositType.SLUSH, ri.getDepositType());
    assertEquals(DepositCoverage.FROM_11_TO_25, ri.getCoverage());
    assertEquals(DepositThickness.THICKNESS_10, ri.getThickness());
    assertEquals(DepositBrakingCapacity.MEDIUM_GOOD, ri.getBrakingCapacity());
  }

  @Test
  public void testParseRunwayDepositWithNotReportedType() {
    Metar m = new Metar();

    command.execute(m, "R05//29294");
    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo ri = m.getRunways().get(0);

    assertEquals("05", ri.getName());
    assertEquals(DepositType.NOT_REPORTED, ri.getDepositType());
    assertEquals(DepositCoverage.FROM_11_TO_25, ri.getCoverage());
    assertEquals(DepositThickness.THICKNESS_10, ri.getThickness());
    assertEquals(DepositBrakingCapacity.MEDIUM_GOOD, ri.getBrakingCapacity());
  }

  @Test
  public void testParseRunwayDepositWithInvalidDeposit() {
    Metar m = new Metar();

    command.execute(m, "R05/6292/4");
    assertThat(m.getRunways(), hasSize(0));
  }

  @Test
  public void testParseRunwayWithLessThanIndicatorAndUnit() {
    Metar m = new Metar();

    command.execute(m, "R01L/M0600FT");
    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo ri = m.getRunways().get(0);
    assertEquals("01L", ri.getName());
    assertEquals(Messages.getInstance().getString("Indicator.M"), ri.getIndicator());
    assertEquals(600, ri.getMinRange());
  }

  @Test
  public void testParseRunwayWithGreaterThanIndicator() {
    Metar m = new Metar();

    command.execute(m, "R01L/P0600FT");
    assertThat(m.getRunways(), hasSize(1));
    RunwayInfo ri = m.getRunways().get(0);
    assertEquals("01L", ri.getName());
    assertEquals(Messages.getInstance().getString("Indicator.P"), ri.getIndicator());
    assertEquals(600, ri.getMinRange());
  }
}
