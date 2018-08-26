package model;

/**
 * Represents a city within the Gun Violence Application.
 */
public class City implements ICity{
  private Integer cityId;
  private String cityName;
  private State state;

  /**
   * Creates a city given a cityId, cityName, and stateAbbreviaiton.
   *
   * @param cityId            A unique identifier given to the city
   * @param cityName          The name of the city
   * @param state             The state the city is located in
   */
  public City(Integer cityId, String cityName, State state) {
    this.cityId = cityId;
    this.cityName = cityName;
    this.state = state;
  }
  
  /**
   * Creates a city given a cityId.
   *
   * @param cityId            A unique identifier given to the city
   */
  public City(Integer cityId) {
    this.cityId = cityId;
  }

  /**
   * Gets the cityId from the city.
   *
   * @return the unique identifier for the city
   */
  public Integer getCityId() {
    return cityId;
  }

  /**
   * Sets the ID for the city.
   *
   * @param cityId the new unique identifier for the city
   */
  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  /**
   * Gets the name of the city.
   *
   * @return name of the city
   */
  public String getCityName() {
    return cityName;
  }

  /**
   * Sets the name of the city.
   *
   * @param cityName the new name of the city
   */
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  /**
   * Gets the state that the city is located in.
   *
   * @return state for the state the city is located in
   */
  public State getState() {
    return state;
  }

  /**
   * Sets the state associated with the city.
   *
   * @param state the new state that the city is associated with
   */
  public void setState(State state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "City{" +
        "cityId=" + cityId +
        ", cityName='" + cityName + '\'' +
        ", state=" + state +
        '}';
  }
}
