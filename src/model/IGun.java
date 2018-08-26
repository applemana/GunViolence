package model;

public interface IGun {

  /**
   * Gets the unique identifier given to the gun.
   *
   * @return unique identifier given to the gun
   */
  Integer getGunId();

  /**
   * Sets the unique identifier given to the gun.
   *
   * @param gunId the new unique identifier given to the gun
   */
  void setGunId(Integer gunId);

  /**
   * Gets if the gun was stolen or not.
   *
   * @return true if the gun was stolen, false otherwise
   */
  Boolean getStolen();

  /**
   * Sets if the gun was stolen or not.
   *
   * @param stolen the new stolen status
   */
  void setStolen(Boolean stolen);

  /**
   * Gets the type of gun.
   *
   * @return type of gun
   */
  String getGunType();

  /**
   * Sets the type of gun.
   *
   * @param gunType the new type of gun
   */
  void setGunType(String gunType);

  /**
   * Gets the associated shooting.
   *
   * @return associated shooting
   */
  Shooting getShooting();

  /**
   * Sets the associated shooting.
   *
   * @param shooting the new associated shooting
   */
  void setShootingId(Shooting shooting);

  /**
   * Gets the number of that type of gun used in the shooting.
   *
   * @return number of that type of gun used in the shooting
   */
  Integer getNumberOfGuns();

  /**
   * Sets the number of that type of gun used in the shooting.
   *
   * @param numberOfGuns the new number of that type of gun used in the shooting
   */
  void setNumberOfGuns(Integer numberOfGuns);
}
