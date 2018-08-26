package model;

public interface IFatal {

  /**
   * Gets the number of individuals killed in the shooting.
   *
   * @return number of individuals killed in the shooting
   */
  Integer getNumberKilled();

  /**
   * Sets the number of individuals killed in the shooting.
   *
   * @param numberKilled the new number of individuals killed in the shooting
   */
  void setNumberKilled(Integer numberKilled);

  /**
   * Gets the number of individuals injured in the shooting.
   *
   * @return number of individuals injured in the shooting
   */
  Integer getNumberInjured();

  /**
   * Sets the number of individuals injured in the shooting.
   *
   * @param numberInjured number of individuals injured in the shooting
   */
  void setNumberInjured(Integer numberInjured);
}
