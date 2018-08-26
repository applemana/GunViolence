package model;

import model.Rating.Grade;

public interface IRating {

  /**
   * Gets the unique rating identifier
   *
   * @return unique rating identifier
   */
  Integer getRatingId();

  /**
   * Sets the rating id for the rating
   *
   * @param ratingId the new rating id for the rating
   */
  void setRatingId(Integer ratingId);

  /**
   * Gets the grade for the rating.
   *
   * @return grade for the rating
   */
  Grade getGrade();

  /**
   * Sets the grade for the rating.
   *
   * @param grade the new grade for the rating
   */
  void setGrade(Grade grade);

  /**
   * Gets the reason for the grade.
   *
   * @return reason for the grade
   */
  String getReasonForGrade();

  /**
   * Sets the reason for the grade.
   *
   * @param reasonForGrade the new reason for the grade
   */
  void setReasonForGrade(String reasonForGrade);

  /**
   * Gets the city for the rating.
   *
   * @return city for the rating
   */
  City getCity();

  /**
   * Sets the state abbreviation for the rating.
   *
   * @param city the new city for the rating
   */
  void setCity(City city);

  /**
   * Converts a string into the Grade format for the string value.
   *
   * @param grade string that indicates the grade.
   *
   * @return String that represents the ENUM
   */
  String getGradeFromString(String grade);
}
