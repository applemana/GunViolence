package model;

/**
 * Represents a praticipant in a shooting in the Gun Violence Application.
 */
public class Participant implements IParticipant {
  private Integer participantId;
  private Integer age;
  private String gender;
  private String name;
  private String relationship;
  private ParticipantType participantType;
  private String status;
  private Shooting shooting;
  private Integer participantNum;

  public enum ParticipantType {
    SUBJECT_SUSPECT ("Subject suspect"),
    VICTIM ("Victim");

    private String type;

    ParticipantType(String type) {
      this.type = type;
    }

    public String toString() {
      return this.type;
    }
  }

  /**
   * Creates a new participant given a participantId, age, gender, participantType, status, and
   * shooting.
   * @param participantId    Unique identifier given to the participant
   * @param age              The age of the participant
   * @param gender           The gender of the participant
   * @param name             The name of the participant
   * @param relationship     The relationship of the participant to the victim(s)
   * @param participantType  The type of participant
   * @param status           The status of the participant
   * @param shooting         The associated shooting
   * @param participantNum   The number of participants in the shooting
   */
  public Participant(Integer participantId, Integer age, String gender, String name,
                     String relationship, ParticipantType participantType, String status,
                     Shooting shooting, Integer participantNum) {
    this.participantId = participantId;
    this.age = age;
    this.gender = gender;
    this.name = name;
    this.relationship = relationship;
    this.participantType = participantType;
    this.status = status;
    this.shooting = shooting;
    this.participantNum = participantNum;
  }

  /**
   * Gets the unique identifier given to the participant.
   *
   * @return unique identifier given to the participant
   */
  public Integer getParticipantId() {
    return participantId;
  }

  /**
   * Sets the unique identifier given to the participant.
   *
   * @param participantId the new unique identifier given to the participant
   */
  public void setParticipantId(Integer participantId) {
    this.participantId = participantId;
  }

  /**
   * Gets the age of the participant.
   *
   * @return age of the participant
   */
  public Integer getAge() {
    return age;
  }

  /**
   * Sets the age of the participant.
   *
   * @param age the new age of the participant
   */
  public void setAge(Integer age) {
    this.age = age;
  }

  /**
   * Gets the gender of the participant.
   *
   * @return gender of the participant
   */
  public String getGender() {
    return gender;
  }

  /**
   * Sets the gender of the participant.
   *
   * @param gender the new gender of the participant
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Gets the name of the participant.
   *
   * @return name of the participant
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the participant.
   *
   * @param name the new name of the participant
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the relationship of the participant to the victim(s).
   *
   * @return relationship of the participant to the victim(s)
   */
  public String getRelationship() {
    return relationship;
  }

  /**
   * Sets the relationship of the participant to the victim(s).
   *
   * @param relationship the new relationship of the participant to the victim(s)
   */
  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  /**
   * Gets the type of participant.
   *
   * @return type of participant
   */
  public ParticipantType getParticipantType() {
    return participantType;
  }

  /**
   * Sets the type of participant.
   *
   * @param participantType the new type of participant
   */
  public void setParticipantType(ParticipantType participantType) {
    this.participantType = participantType;
  }

  /**
   * Gets the status of the participant.
   *
   * @return status of the participant
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status of the participant.
   *
   * @param status the new status of the participant.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the associated shooting.
   *
   * @return associated shooting
   */
  public Shooting getShooting() {
    return shooting;
  }

  /**
   * Sets the associated shooting identifier.
   *
   * @param shooting the new associated shooting
   */
  public void setShooting(Shooting shooting) {
    this.shooting = shooting;
  }

  /**
   * Gets the number of participants in the shooting.
   *
   * @return number of participants in the shooting
   */
  public Integer getParticipantNum() {
    return participantNum;
  }

  /**
   * Sets the number of participants in the shooting.
   *
   * @param participantNum the new number of participants in the shooting
   */
  public void setParticipantNum(Integer participantNum) {
    this.participantNum = participantNum;
  }
}
