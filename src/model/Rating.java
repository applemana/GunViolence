package model;


/**
 * Represents a rating given to a location within the Gun Violence Application.
 */
public class Rating implements IRating {
  private Integer ratingId;
  private Grade grade;
  private String reasonForGrade;
  private City city;

  public enum Grade {
    A_PLUS ("A_PLUS"), A ("A"), A_MINUS ("A_MINUS"),
    B_PLUS ("B_PLUS"), B ("B"), B_MINUS ("B_MINUS"),
    C_PLUS ("C_PLUS"), C ("C_PLUS"), C_MINUS ("C_MINUS"),
    D_PLUS ("D_PLUS"), D ("D"), D_MINUS ("D_MINUS"),
    F ("F");

    private final String grade;

    Grade(String grade) {
      this.grade = grade;
    }

    public String toString() {
      return this.grade;
    }

    public String prettyPrint() {
      switch(this) {
        case A_PLUS: return "A+";
        case A: return "A";
        case A_MINUS: return "A-";
        case B_PLUS: return "B+";
        case B: return "B";
        case B_MINUS:return "B-";
        case C_PLUS: return "C+";
        case C: return "C";
        case C_MINUS: return "C-";
        case D_PLUS: return "D+";
        case D: return "D";
        case D_MINUS: return "D-";
        case F: return "F";
      }
      return "F";
    }
  }

  /**
   * Creates a new rating given a ratingId, grade, reasonForGrade, and stateAbbreviation.
   *
   * @param ratingId          The unique identifier given to the rating
   * @param grade             The grade given to the state
   * @param reasonForGrade    The reason for the grade given
   * @param city              The city that the grade is for
   */
  public Rating(Integer ratingId, Grade grade, String reasonForGrade, City city) {
    this.ratingId = ratingId;
    this.grade = grade;
    this.reasonForGrade = reasonForGrade;
    this.city = city;
  }
  
  public Rating(Grade grade, String reasonForGrade, City city) {
	    this.grade = grade;
	    this.reasonForGrade = reasonForGrade;
	    this.city = city;
	  }
  
  public Rating(Integer ratingId) {
	  this.ratingId = ratingId;
	  }

	  public Rating() {}

  /**
   * Gets the unique rating identifier
   *
   * @return unique rating identifier
   */
  public Integer getRatingId() {
    return ratingId;
  }

  /**
   * Sets the rating id for the rating
   *
   * @param ratingId the new rating id for the rating
   */
  public void setRatingId(Integer ratingId) {
    this.ratingId = ratingId;
  }

  /**
   * Gets the grade for the rating.
   *
   * @return grade for the rating
   */
  public Grade getGrade() {
    return grade;
  }

  /**
   * Sets the grade for the rating.
   *
   * @param grade the new grade for the rating
   */
  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  /**
   * Gets the reason for the grade.
   *
   * @return reason for the grade
   */
  public String getReasonForGrade() {
    return reasonForGrade;
  }

  /**
   * Sets the reason for the grade.
   *
   * @param reasonForGrade the new reason for the grade
   */
  public void setReasonForGrade(String reasonForGrade) {
    this.reasonForGrade = reasonForGrade;
  }

  /**
   * Gets the city for the rating.
   *
   * @return city for the rating
   */
  public City getCity() {
    return city;
  }

  /**
   * Sets the state abbreviation for the rating.
   *
   * @param city the new city for the rating
   */
  public void setCity(City city) {
    this.city = city;
  }

  /**
   * Converts a string into the Grade format for the string value.
   *
   * @param grade string that indicates the grade.
   *
   * @return String that represents the ENUM
   */
  public String getGradeFromString(String grade) {
    switch(grade) {
      case "A+": return "A_PLUS";
      case "A": return "A";
      case "A-": return "A_MINUS";
      case "B+": return "B_PLUS";
      case "B": return "B";
      case "B-": return "B_MINUS";
      case "C+": return "C_PLUS";
      case "C": return "C";
      case "C-": return "C_MINUS";
      case "D+": return "D_PLUS";
      case "D": return "D";
      case "D-": return "D_PLUS";
      case "F": return "F";
    }
    return grade;
  }
}
