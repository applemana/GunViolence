package model;

import java.util.Date;

import model.Law.Effect;

public interface ILaw {

  /**
   * Gets the unique identifier given to the law.
   *
   * @return unique identifier given to the law
   */
  Integer getLawId();

  /**
   * Sets the unique identifier given to the law.
   *
   * @param lawId unique identifier given to the law
   */
  void setLawId(Integer lawId);

  /**
   * Gets the effect the law has had on gun use.
   *
   * @return effect the law has had on gun use
   */
  Effect getEffect();

  /**
   * Sets the effect the law has had on gun use.
   *
   * @param effect the new effect the law has had on gun use
   */
  void setEffect(Effect effect);

  /**
   * Gets the contents of the law.
   *
   * @return contents of the law
   */
  String getContents();

  /**
   * Sets the contents of the law.
   *
   * @param contents the new contents of the law
   */
  void setContents(String contents);

  /**
   * Gets the category of the law.
   *
   * @return category of the law
   */
  String getCategory();

  /**
   * Sets the category of the law.
   *
   * @param category the new category of the law
   */
  void setCategory(String category);

  /**
   * Gets the year the law was passed.
   *
   * @return year the law was passed
   */
  public Date getYear();

  /**
   * Sets the year the law was passed.
   *
   * @param year year the law was passed
   */
  void setYear(Date year);

  /**
   * Gets the state where the law is in effect.
   *
   * @return state where the law is in effect
   */
  State getState();

  /**
   * Sets the state abbreviation where the law is in effect.
   *
   * @param stateAbbreviation state abbreviation where the law is in effect
   */
  void setState(State stateAbbreviation);
}
