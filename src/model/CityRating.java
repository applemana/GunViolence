package model;

/**
 * This represents a city and its rating.
 */
public class CityRating implements ICityRating{
  private City city;
  private Rating rating;

  /**
   * Creates a CityRating when given a city and a corresponding rating.
   *
   * @param city   the city that the rating belongs to
   * @param rating the rating that the city has
   */
  public CityRating(City city, Rating rating) {
    this.city = city;
    this.rating = rating;
  }

  /**
   * Gets the city
   *
   * @return city
   */
  public City getCity() {
    return city;
  }

  /**
   * Sets the city
   *
   * @param city the city being set
   */
  public void setCity(City city) {
    this.city = city;
  }

  /**
   * Gets the rating
   *
   * @return rating
   */
  public Rating getRating() {
    return rating;
  }

  /**
   * Sets the rating
   *
   * @param rating the rating being set
   */
  public void setRating(Rating rating) {
    this.rating = rating;
  }
}
