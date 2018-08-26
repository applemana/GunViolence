package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Participant;
import model.Shooting;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link Participant} into your MySQL instance and retrieve
 * {@link Participant} from MySQL instance.
 */
public class ParticipantDao {
  private ConnectionManager connectionManager;

  private static ParticipantDao instance = null;

  private ParticipantDao() {
    connectionManager = new ConnectionManager();
  }

  public static ParticipantDao getInstance() {
    if (instance == null) {
      instance = new ParticipantDao();
    }
    return instance;
  }

  /**
   * Inserts a participant into the database.
   *
   * @param participant the participant that will be added to the database
   * @return newly created participant
   * @throws SQLException if communication with MySQL fails.
   */
  public Participant create(Participant participant) throws SQLException {
    String insertCity = "INSERT INTO Participants(ParticipantId, Age, Gender, Name, " +
        "Relationship, Type, Status, ShootingId, ParticipantNum) VALUES(?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, participant.getParticipantId());
      insertStmt.setInt(2, participant.getAge());
      insertStmt.setString(3, participant.getGender());
      insertStmt.setString(4, participant.getName());
      insertStmt.setString(5, participant.getRelationship());
      insertStmt.setString(6, participant.getParticipantType().toString());
      insertStmt.setString(7, participant.getStatus());
      insertStmt.setInt(8, participant.getShooting().getShootingId());
      insertStmt.setInt(9, participant.getParticipantNum());
      insertStmt.executeUpdate();

      return participant;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }


  /**
   * Gets a list of participants given a shooting.
   *
   * @param shooting the shooting the user would like the participants from
   * @return list of shooting participants
   * @throws SQLException if communication with MySQL fails.
   */
  public List<Participant> getParticipantsOfShooting(Shooting shooting) throws SQLException {
    List<Participant> shootingParticipants = new ArrayList<Participant>();
    String selectParticipants =
        "SELECT * FROM Participants WHERE ShootingId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectParticipants);
      selectStmt.setInt(1, shooting.getShootingId());
      results = selectStmt.executeQuery();

      while (results.next()) {
        int participantId = results.getInt("ParticipantId");
        int age = results.getInt("Age");
        String gender = results.getString("Gender");
        String name = results.getString("Name");
        String relationship = results.getString("Relationship");
        String type = results.getString("Type");
        String status = results.getString("Status");
        int shootingId = results.getInt("ShootingId");
        int participantNum = results.getInt("ParticipantNum");

        Participant participant = new Participant(participantId, age, gender, name, relationship,
            Participant.ParticipantType.valueOf(type), status,
            new ShootingDao().getShootingFromShootingId(shootingId), participantNum);

        shootingParticipants.add(participant);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return shootingParticipants;
  }

  /**
   * Gets the number of participants in the database for a particular shooting.
   *
   * @param shooting the shootings the user would like the know the number of participants for
   *
   * @return number of participants in the database for shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public int getNumberParticipantsInShooting(Shooting shooting) throws SQLException {
    int numberOfParticipants = 0;
    String selectGuns =
        "SELECT COUNT(*) as count FROM Participants WHERE ShootingId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectGuns);
      selectStmt.setInt(1, shooting.getShootingId());
      results = selectStmt.executeQuery();

      while (results.next()) {
        numberOfParticipants = results.getInt("count");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return numberOfParticipants;
  }

  /**
   * Deletes a participant from the database.
   *
   * @param participant the participant to be deleted from the database
   * @return deleted participant
   * @throws SQLException if communication with MySQL fails.
   */
  public Participant delete(Participant participant) throws SQLException {
    String deleteParticipant = "DELETE FROM Participants WHERE ParticipantID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteParticipant);
      deleteStmt.setInt(1, participant.getParticipantId());
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
