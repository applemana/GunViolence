package model;

import model.Participant.ParticipantType;

public interface IParticipant {

  /**
   * Gets the unique identifier given to the participant.
   *
   * @return unique identifier given to the participant
   */
  Integer getParticipantId();

  /**
   * Sets the unique identifier given to the participant.
   *
   * @param participantId the new unique identifier given to the participant
   */
  void setParticipantId(Integer participantId);

  /**
   * Gets the age of the participant.
   *
   * @return age of the participant
   */
  Integer getAge();

  /**
   * Sets the age of the participant.
   *
   * @param age the new age of the participant
   */
  void setAge(Integer age);

  /**
   * Gets the gender of the participant.
   *
   * @return gender of the participant
   */
  String getGender();

  /**
   * Sets the gender of the participant.
   *
   * @param gender the new gender of the participant
   */
  void setGender(String gender);

  /**
   * Gets the name of the participant.
   *
   * @return name of the participant
   */
  String getName();

  /**
   * Sets the name of the participant.
   *
   * @param name the new name of the participant
   */
  void setName(String name);

  /**
   * Gets the relationship of the participant to the victim(s).
   *
   * @return relationship of the participant to the victim(s)
   */
  String getRelationship();

  /**
   * Sets the relationship of the participant to the victim(s).
   *
   * @param relationship the new relationship of the participant to the victim(s)
   */
  void setRelationship(String relationship);

  /**
   * Gets the type of participant.
   *
   * @return type of participant
   */
  ParticipantType getParticipantType();

  /**
   * Sets the type of participant.
   *
   * @param participantType the new type of participant
   */
  void setParticipantType(ParticipantType participantType);

  /**
   * Gets the status of the participant.
   *
   * @return status of the participant
   */
  String getStatus();

  /**
   * Sets the status of the participant.
   *
   * @param status the new status of the participant.
   */
  void setStatus(String status);

  /**
   * Gets the associated shooting.
   *
   * @return associated shooting
   */
  Shooting getShooting();

  /**
   * Sets the associated shooting identifier.
   *
   * @param shooting the new associated shooting
   */
  void setShooting(Shooting shooting);

  /**
   * Gets the number of participants in the shooting.
   *
   * @return number of participants in the shooting
   */
  Integer getParticipantNum();

  /**
   * Sets the number of participants in the shooting.
   *
   * @param participantNum the new number of participants in the shooting
   */
  void setParticipantNum(Integer participantNum);
}
