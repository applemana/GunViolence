package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.School;
import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link School} into your MySQL instance and retrieve
 * {@link School} from MySQL instance.
 */
public class SchoolDao {
  private ConnectionManager connectionManager;

  private static SchoolDao instance = null;

  private SchoolDao() {
    connectionManager = new ConnectionManager();
  }

  public static SchoolDao getInstance() {
    if (instance == null) {
      instance = new SchoolDao();
    }
    return instance;
  }

  /**
   * Inserts a school shooting in the database.
   *
   * @param school the school shooting that will be added to the database.
   * @return newly created school shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public School create(School school) throws SQLException {
    String insertCity = "INSERT INTO School(ShootingId, TypeOfSchool, NumberKilled) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, school.getShootingId());
      insertStmt.setString(2, school.getSchoolType().toString());
      insertStmt.setInt(3, school.getNumberKilled());
      insertStmt.executeUpdate();

      return school;
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
   * Gets a list of the 10 most recent school shooting within a state.
   *
   * @param state the state the user would like the 10 most recent school shooting for
   * @return list of 10 most recent school shooting from state
   * @throws SQLException if communication with MySQL fails.
   */
  public List<School> getStatesLastTenSchoolShootings(State state) throws SQLException {
    List<School> statesSchools = new ArrayList<>();
    String selectSchools =
        "SELECT *\n" +
            "FROM States\n" +
            "  INNER JOIN Cities\n" +
            "    ON States.StateAbbreviation = Cities.StateAbbreviation\n" +
            "  INNER JOIN Shootings\n" +
            "    ON Cities.CityId = Shootings.CityId\n" +
            "  INNER Join School\n" +
            "    ON School.ShootingId = Shootings.ShootingId\n" +
            "WHERE Cities.StateAbbreviation = ?\n" +
            "ORDER BY ShootingDate DESC\n" +
            "LIMIT 10";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectSchools);
      selectStmt.setString(1, state.getStateAbbreviation());
      results = selectStmt.executeQuery();

      while (results.next()) {
        int shootingId = results.getInt("ShootingId");
        String schoolType = results.getString("TypeOfSchool");
        int numberKilled = results.getInt("NumberKilled");
        int numberGuns = results.getInt("NumberOfGuns");
        String characteristics = results.getString("Characteristics");
        Date shootingDate = results.getDate("ShootingDate");
        int cityId = results.getInt("CityId");

        School school = new School(shootingId, new CityDao().getCityFromCityId(cityId),
            shootingDate, numberGuns, characteristics, School.SchoolType.valueOf(schoolType),
            numberKilled);

        statesSchools.add(school);
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
    return statesSchools;
  }

  /**
   * Deletes a school shooting from the database.
   *
   * @param school the school shooting to be deleted from the database
   * @return deleted school shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public School delete(School school) throws SQLException {
    String deleteSchool = "DELETE FROM School WHERE ShootingID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteSchool);
      deleteStmt.setInt(1, school.getShootingId());
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

