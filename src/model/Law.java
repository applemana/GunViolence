package model;

import java.util.Date;

/**
 * Represents a law in the Gun Violence Application.
 */
public class Law implements ILaw {
  private Integer lawId;
  private Effect effect;
  private String contents;
  private String category;
  private Date year;
  private State state;


  public enum Effect {
    RESTRICIVE ("Restrictive"),
    PERMISSIVE ("Permissive"),
    OTHER ("Other");

    private final String effect;

    Effect(String effect) {
      this.effect = effect;
    }

    public String toString() {
      return this.effect;
    }
  }

  /**
   * Creates a new law given a lawId, effect, contents, category, year, and stateAbbreviation.
   *
   * @param lawId              The unique identifier given to the law
   * @param effect             The effect the law has had on gun use
   * @param contents           The contents of the law
   * @param category           The category of the law
   * @param year               The year the law was passed
   * @param state              The state where the law is in effect
   */
  public Law(Integer lawId, Effect effect, String contents, String category, Date year,
             State state) {
    this.lawId = lawId;
    this.effect = effect;
    this.contents = contents;
    this.category = category;
    this.year = year;
    this.state = state;
  }

  /**
   * Gets the unique identifier given to the law.
   *
   * @return unique identifier given to the law
   */
  public Integer getLawId() {
    return lawId;
  }

  /**
   * Sets the unique identifier given to the law.
   *
   * @param lawId unique identifier given to the law
   */
  public void setLawId(Integer lawId) {
    this.lawId = lawId;
  }

  /**
   * Gets the effect the law has had on gun use.
   *
   * @return effect the law has had on gun use
   */
  public Effect getEffect() {
    return effect;
  }

  /**
   * Sets the effect the law has had on gun use.
   *
   * @param effect the new effect the law has had on gun use
   */
  public void setEffect(Effect effect) {
    this.effect = effect;
  }

  /**
   * Gets the contents of the law.
   *
   * @return contents of the law
   */
  public String getContents() {
    return contents;
  }

  /**
   * Sets the contents of the law.
   *
   * @param contents the new contents of the law
   */
  public void setContents(String contents) {
    this.contents = contents;
  }

  /**
   * Gets the category of the law.
   *
   * @return category of the law
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the category of the law.
   *
   * @param category the new category of the law
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Gets the year the law was passed.
   *
   * @return year the law was passed
   */
  public Date getYear() {
    return year;
  }

  /**
   * Sets the year the law was passed.
   *
   * @param year year the law was passed
   */
  public void setYear(Date year) {
    this.year = year;
  }

  /**
   * Gets the state where the law is in effect.
   *
   * @return state where the law is in effect
   */
  public State getState() {
    return state;
  }

  /**
   * Sets the state abbreviation where the law is in effect.
   *
   * @param stateAbbreviation state abbreviation where the law is in effect
   */
  public void setState(State stateAbbreviation) {
    this.state = stateAbbreviation;
  }
}
