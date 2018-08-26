package model;

/**
 * Represents a state within the Gun Violence Application.
 */
public class State {
  private String stateAbbreviation;
  private String stateName;

  /**
   * Creates a new state given the stateAbbreviation and stateName.
   *
   * @param stateAbbreviation The unique 2 letter abbreviation for the state
   * @param stateName         The name of the state
   */
  public State(String stateAbbreviation, String stateName) {
    this.stateAbbreviation = stateAbbreviation;
    this.stateName = stateName;
  }
  
  /**
   * Creates a new state given the stateAbbreviation.
   *
   * @param stateAbbreviation The unique 2 letter abbreviation for the state
   */
  public State(String stateAbbreviation) {
	  this.stateAbbreviation = stateAbbreviation;
  }

  /**
   * Gets the state abbreviation.
   *
   * @return unique state abbreviation
   */
  public String getStateAbbreviation() {
    return stateAbbreviation;
  }

  /**
   * Sets the state abbreviation.
   *
   * @param stateAbbreviation the new unique state abbreviation
   */
  public void setStateAbbreviation(String stateAbbreviation) {
    this.stateAbbreviation = stateAbbreviation;
  }

  /**
   * Gets the name of the state.
   *
   * @return name of the state
   */
  public String getStateName() {
    return stateName;
  }

  /**
   * Sets the name of the state
   *
   * @param stateName the new name of the state
   */
  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  @Override
  public String toString() {
    return "State{" +
        "stateAbbreviation='" + stateAbbreviation + '\'' +
        ", stateName='" + stateName + '\'' +
        '}';
  }
}
