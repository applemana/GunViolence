package model;

public interface ICity {

  /**
   * Gets the cityId from the city.
   *
   * @return the unique identifier for the city
   */
  Integer getCityId();

  /**
   * Sets the ID for the city.
   *
   * @param cityId the new unique identifier for the city
   */
  void setCityId(Integer cityId);

  /**
   * Gets the name of the city.
   *
   * @return name of the city
   */
  String getCityName();

  /**
   * Sets the name of the city.
   *
   * @param cityName the new name of the city
   */
  void setCityName(String cityName);

  /**
   * Gets the state that the city is located in.
   *
   * @return state for the state the city is located in
   */
  State getState();

  /**
   * Sets the state associated with the city.
   *
   * @param state the new state that the city is associated with
   */
  void setState(State state);
}
