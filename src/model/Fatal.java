package model;

import java.util.Date;

/**
 * Represents a shooting where individuals were killed in the Gun Violence Application.
 */
public class Fatal extends Shooting implements IFatal{
  private Integer numberKilled;
  private Integer numberInjured;

  /**
   * Creates a new fatal shooting given the schoolType and the numberKilled.
   *
   * @param shootingId      Unique identifier given to the shooing
   * @param city            The city for the city the shooting was in
   * @param shootingDate    The date the shooting took place on
   * @param numberOfGuns    The number of guns involved in the shooting
   * @param characteristics The characteristics of the shooting
   * @param numberKilled    The number of individuals killed in the shooting
   * @param numberInjured   The number of individuals injured in the shooting
   */
  public Fatal(Integer shootingId, City city, Date shootingDate, Integer numberOfGuns,
               String characteristics, Integer numberKilled,
               Integer numberInjured) {
    super(shootingId, city, shootingDate, numberOfGuns, characteristics);
    this.numberKilled = numberKilled;
    this.numberInjured = numberInjured;
  }

  /**
   * Gets the number of individuals killed in the shooting.
   *
   * @return number of individuals killed in the shooting
   */
  public Integer getNumberKilled() {
    return numberKilled;
  }

  /**
   * Sets the number of individuals killed in the shooting.
   *
   * @param numberKilled the new number of individuals killed in the shooting
   */
  public void setNumberKilled(Integer numberKilled) {
    this.numberKilled = numberKilled;
  }

  /**
   * Gets the number of individuals injured in the shooting.
   *
   * @return number of individuals injured in the shooting
   */
  public Integer getNumberInjured() {
    return numberInjured;
  }

  /**
   * Sets the number of individuals injured in the shooting.
   *
   * @param numberInjured number of individuals injured in the shooting
   */
  public void setNumberInjured(Integer numberInjured) {
    this.numberInjured = numberInjured;
  }
}
