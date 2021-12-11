package io.github.mivek.factory;

/**
 * @author Jean-Kevin KPADEY
 * @param <T> Model element the factory must create.
 */
public interface AbstractFactory<T> {

  /**
   * Create a type of object depending on the discriminant.
   * @param discriminant String to identify the type of object to create.
   * @return An instance of the new object.
   */
  T create(String discriminant);
}
