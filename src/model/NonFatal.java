package model;

import java.util.Date;

public class NonFatal extends Shooting implements INonFatal {
  private Integer numberInjured;

  /**
   * Creates a new non-fatal shooting given the schoolType and the numberKilled.
   *
   * @param shootingId      Unique identifier given to the shooing
   * @param city            The city for the city the shooting was in
   * @param shootingDate    The date the shooting took place on
   * @param numberOfGuns    The number of guns involved in the shooting
   * @param characteristics The characteristics of the shooting
   * @param numberInjured   The number of individuals injured in the shooting
   */
  public NonFatal(Integer shootingId, City city, Date shootingDate, Integer numberOfGuns,
                  String characteristics, Integer numberInjured) {
    super(shootingId, city, shootingDate, numberOfGuns, characteristics);
    this.numberInjured = numberInjured;
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
