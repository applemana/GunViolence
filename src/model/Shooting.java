package model;

import java.util.Date;

/**
 * Represents a shooting in the Gun Violence Application.
 */
public class Shooting {
  private Integer shootingId;
  private City city;
  private Date shootingDate;
  private Integer numberOfGuns;
  private String characteristics;

  /**
   * Creates a new shooting given a shootingId, city, shootingDate, numberOfGuns, characteristics,
   * and incidentId.
   *
   * @param shootingId      Unique identifier given to the shooing
   * @param city            The city for the city the shooting was in
   * @param shootingDate    The date the shooting took place on
   * @param numberOfGuns    The number of guns involved in the shooting
   * @param characteristics The characteristics of the shooting
   */
  public Shooting(Integer shootingId, City city, Date shootingDate, Integer numberOfGuns,
                  String characteristics) {
    this.shootingId = shootingId;
    this.city = city;
    this.shootingDate = shootingDate;
    this.numberOfGuns = numberOfGuns;
    this.characteristics = characteristics;
  }
  
  /**
   * Creates a new shooting given a shootingId
   *
   * @param shootingId      Unique identifier given to the shooing
   */
  public Shooting(Integer shootingId) {
    this.shootingId = shootingId;
  }

  /**
   * Gets the shooting identifier for the shooting.
   *
   * @return shooting identifier
   */
  public Integer getShootingId() {
    return shootingId;
  }

  /**
   * Sets the shooting identifier for the shooting.
   *
   * @param shootingId the new shooting identifier for the shooting
   */
  public void setShootingId(Integer shootingId) {
    this.shootingId = shootingId;
  }

  /**
   * Gets the city where the shooting took place.
   *
   * @return city where the shooting took place
   */
  public City getCity() {
    return city;
  }

  /**
   * Sets the city where the shooting took place.
   *
   * @param city the new city where the shooting took place
   */
  public void setCityId(City city) {
    this.city = city;
  }

  /**
   * Gets the date the shooting took place.
   *
   * @return date the shooting took place
   */
  public Date getShootingDate() {
    return shootingDate;
  }

  /**
   * Sets the date the shooting took place.
   *
   * @param shootingDate the new date the shooting took place
   */
  public void setShootingDate(Date shootingDate) {
    this.shootingDate = shootingDate;
  }

  /**
   * Gets the number of guns involved in the shooting.
   *
   * @return number of guns involved in the shooting
   */
  public Integer getNumberOfGuns() {
    return numberOfGuns;
  }

  /**
   * Sets the number of guns involved in the shooting.
   *
   * @param numberOfGuns number of guns involved in the shooting
   */
  public void setNumberOfGuns(Integer numberOfGuns) {
    this.numberOfGuns = numberOfGuns;
  }

  /**
   * Gets the characteristics of the shooting.
   *
   * @return characteristics of the shooting
   */
  public String getCharacteristics() {
    return characteristics;
  }

  /**
   * Sets the characteristics of the shooting.
   *
   * @param characteristics the new characteristics of the shooting
   */
  public void setCharacteristics(String characteristics) {
    this.characteristics = characteristics;
  }
}
