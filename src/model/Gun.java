package model;

/**
 * Represents a gun used in a shooting in the Gun Violence Application.
 */
public class Gun implements IGun {
  private Integer gunId;
  private Boolean stolen;
  private String gunType;
  private Shooting shooting;
  private Integer numberOfGuns;

  /**
   * Creates a new gun given a gunId, whether it was stolen or not, and a shooting.
   * @param gunId        The unique identifier given to the gun
   * @param stolen       True if the gun was stolen, false otherwise
   * @param gunType      The type of gun
   * @param shooting     The associated shooting
   * @param numberOfGuns The number of that type of gun used in the shooting
   */
  public Gun(Integer gunId, Boolean stolen, String gunType, Shooting shooting, Integer numberOfGuns) {
    this.gunId = gunId;
    this.stolen = stolen;
    this.gunType = gunType;
    this.shooting = shooting;
    this.numberOfGuns = numberOfGuns;
  }

  /**
   * Gets the unique identifier given to the gun.
   *
   * @return unique identifier given to the gun
   */
  public Integer getGunId() {
    return gunId;
  }

  /**
   * Sets the unique identifier given to the gun.
   *
   * @param gunId the new unique identifier given to the gun
   */
  public void setGunId(Integer gunId) {
    this.gunId = gunId;
  }

  /**
   * Gets if the gun was stolen or not.
   *
   * @return true if the gun was stolen, false otherwise
   */
  public Boolean getStolen() {
    return stolen;
  }

  /**
   * Sets if the gun was stolen or not.
   *
   * @param stolen the new stolen status
   */
  public void setStolen(Boolean stolen) {
    this.stolen = stolen;
  }

  /**
   * Gets the type of gun.
   *
   * @return type of gun
   */
  public String getGunType() {
    return gunType;
  }

  /**
   * Sets the type of gun.
   *
   * @param gunType the new type of gun
   */
  public void setGunType(String gunType) {
    this.gunType = gunType;
  }

  /**
   * Gets the associated shooting.
   *
   * @return associated shooting
   */
  public Shooting getShooting() {
    return shooting;
  }

  /**
   * Sets the associated shooting.
   *
   * @param shooting the new associated shooting
   */
  public void setShootingId(Shooting shooting) {
    this.shooting = shooting;
  }

  /**
   * Gets the number of that type of gun used in the shooting.
   *
   * @return number of that type of gun used in the shooting
   */
  public Integer getNumberOfGuns() {
    return numberOfGuns;
  }

  /**
   * Sets the number of that type of gun used in the shooting.
   *
   * @param numberOfGuns the new number of that type of gun used in the shooting
   */
  public void setNumberOfGuns(Integer numberOfGuns) {
    this.numberOfGuns = numberOfGuns;
  }
}
