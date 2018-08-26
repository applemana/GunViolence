package model;

import java.util.Date;

/**
 * Represents a school shooting in the Gun Violence Application.
 */
public class School extends Shooting implements ISchool {
  private SchoolType schoolType;
  private Integer numberKilled;

  public enum SchoolType {
    C  ("College"),
    HS ("High School"),
    MS ("Middle School"),
    ES ("Elementary School"),
    NA ("Unknown");

    private String schoolType;

    SchoolType(String schoolType) {
      this.schoolType = schoolType;
    }

    public String toString() {
      return this.schoolType;
    }

  }

  /**
   * Creates a new school shooting given the schoolType and the numberKilled.
   *
   * @param shootingId      Unique identifier given to the shooing
   * @param city            The city for the city the shooting was in
   * @param shootingDate    The date the shooting took place on
   * @param numberOfGuns    The number of guns involved in the shooting
   * @param characteristics The characteristics of the shooting
   * @param schoolType   The type of school that the shooting took place in
   * @param numberKilled The number of individuals killed in the shooting
   */
  public School(Integer shootingId, City city, Date shootingDate, Integer numberOfGuns,
                String characteristics, SchoolType schoolType,
                Integer numberKilled) {
    super(shootingId, city, shootingDate, numberOfGuns, characteristics);
    this.schoolType = schoolType;
    this.numberKilled = numberKilled;
  }

  /**
   * Gets the type of school that the shooting took place in.
   *
   * @return type of school that the shooting took place in
   */
  public SchoolType getSchoolType() {
    return schoolType;
  }

  /**
   * Sets the type of school that the shooting took place in.
   *
   * @param schoolType the new type of school that the shooting took place in
   */
  public void setSchoolType(SchoolType schoolType) {
    this.schoolType = schoolType;
  }

  /**
   * Gets the number of individuals killed in the shooting.
   *
   * @return number of individuals killed in the shooting
   */
  public Integer getNumberKilled() {
    return numberKilled;
  }

  /**
   * Sets the number of individuals killed in the shooting.
   *
   * @param numberKilled the new number of individuals killed in the shooting
   */
  public void setNumberKilled(Integer numberKilled) {
    this.numberKilled = numberKilled;
  }

  /**
   * Gets the SchoolType from a string value
   *
   * @param schoolType a string value representing the SchoolType
   *
   * @return SchoolType associated with the schoolType string
   */
  public String getTypeFromString(String schoolType) {
    switch(schoolType) {
      case "College": return "C";
      case "High School": return "HS";
      case "Middle School": return "MS";
      case "Elementary School": return "ES";
      case "Unknown": return "NA";
    }
    return schoolType;
  }

}
