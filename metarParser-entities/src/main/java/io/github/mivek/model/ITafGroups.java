package io.github.mivek.model;

import java.util.List;

/**
 * @author Jean-Kevin KPADEY
 */
public interface ITafGroups {

  /**
   * @return The list of turbulence.
   */
  List<Turbulence> getTurbulences();

  /**
   * @param turbulence The turbulence to add.
   */
  default void addTurbulence(Turbulence turbulence) {
    getTurbulences().add(turbulence);
  }

  /**
   * @return The list of icing elements.
   */
  List<Icing> getIcings();

  /**
   * @param icing The icing object to add.
   */
  default void addIcing(Icing icing) {
    getIcings().add(icing);
  }
}
