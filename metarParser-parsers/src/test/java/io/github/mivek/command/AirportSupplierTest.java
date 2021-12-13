package io.github.mivek.command;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.github.mivek.model.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class AirportSupplierTest {

  private AirportSupplier sut;

  @BeforeEach
  public void setUp() {
    sut = new AirportSupplier();
  }

  @Test
  public void testGetWithExistingIcao() {
    Airport res = sut.get("LFPG");
    assertNotNull(res);
  }

  @Test
  public void testWithNonExistingIcao() {
    Airport res = sut.get("AA");
    assertNull(res);
  }
}
