package model;

public interface ICityRating {

  /**
   * Gets the city
   *
   * @return city
   */
  City getCity();

  /**
   * Sets the city
   *
   * @param city the city being set
   */
  void setCity(City city);

  /**
   * Gets the rating
   *
   * @return rating
   */
  Rating getRating();

  /**
   * Sets the rating
   *
   * @param rating the rating being set
   */
  void setRating(Rating rating);
}
