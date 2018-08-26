package model;

import model.School.SchoolType;

public interface ISchool {

  /**
   * Gets the type of school that the shooting took place in.
   *
   * @return type of school that the shooting took place in
   */
  SchoolType getSchoolType();

  /**
   * Sets the type of school that the shooting took place in.
   *
   * @param schoolType the new type of school that the shooting took place in
   */
  void setSchoolType(SchoolType schoolType);

  /**
   * Gets the number of individuals killed in the shooting.
   *
   * @return number of individuals killed in the shooting
   */
  Integer getNumberKilled();

  /**
   * Sets the number of individuals killed in the shooting.
   *
   * @param numberKilled the new number of individuals killed in the shooting
   */
  void setNumberKilled(Integer numberKilled);

  /**
   * Gets the SchoolType from a string value
   *
   * @param schoolType a string value representing the SchoolType
   *
   * @return SchoolType associated with the schoolType string
   */
  String getTypeFromString(String schoolType);
}
