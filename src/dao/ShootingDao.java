package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.City;
import model.Shooting;
import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link Shooting} into your MySQL instance and retrieve
 * {@link Shooting} from MySQL instance.
 */
public class ShootingDao {
  private ConnectionManager connectionManager;

  private static ShootingDao instance = null;

  protected ShootingDao() {
    connectionManager = new ConnectionManager();
  }

  public static ShootingDao getInstance() {
    if (instance == null) {
      instance = new ShootingDao();
    }
    return instance;
  }

  /**
   * Inserts a shooting into the database.
   *
   * @param shooting the shooting to be added to the database
   * @return newly created shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public Shooting create(Shooting shooting) throws SQLException {
    String insertCity = "INSERT INTO Shootings(CityId, ShootingDate, NumberOfGuns, " +
        "Characteristics) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, shooting.getCity().getCityId());
      insertStmt.setString(2, new java.sql.Date(shooting.getShootingDate().getTime()).toString());
      insertStmt.setInt(3, shooting.getNumberOfGuns());
      insertStmt.setString(4, shooting.getCharacteristics());
      insertStmt.executeUpdate();

      return getNewestShooting();

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
   * Gets the most recent shooting.
   *
   * @return most recent shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public Shooting getNewestShooting() throws SQLException {
    String selectShooting = "SELECT *\n" +
        "  FROM States\n" +
        "    INNER JOIN(\n" +
        "      Select Cities.CityId, CityName, StateAbbreviation, ShootingId, ShootingDate, NumberOfGuns, Characteristics\n" +
        "      FROM Cities\n" +
        "        INNER JOIN Shootings ON Shootings.CityId = Cities.CityId\n" +
        "      ) AS citiesShootings\n" +
        "    ON citiesShootings.StateAbbreviation = States.StateAbbreviation\n" +
        "  ORDER BY ShootingId DESC\n" +
        "  LIMIT 1";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectShooting);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String stateAbbreviation = results.getString("StateAbbreviation");
        String stateName = results.getString("StateName");
        String cityName = results.getString("CityName");
        int cityId = results.getInt("CityId");
        int shootingId = results.getInt("ShootingId");
        Date shootingDate = results.getDate("ShootingDate");
        int numberOfGuns = results.getInt("NumberOfGuns");
        String characteristics = results.getString("Characteristics");

        State state = new State(stateAbbreviation, stateName);
        City city = new City(cityId, cityName, state);

        return new Shooting(shootingId, city, shootingDate, numberOfGuns, characteristics);
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
    return null;
  }

  /**
   * Gets a shooting given the shootings unique identifier.
   *
   * @param shootingId the shootings unique identifier
   * @return shooting associated with the given shootingid
   * @throws SQLException if communication with MySQL fails.
   */
  public Shooting getShootingFromShootingId(Integer shootingId) throws SQLException {
    String selectShooting = "SELECT * FROM Shootings WHERE ShootingId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectShooting);
      selectStmt.setInt(1, shootingId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int cityId = results.getInt("CityId");
        Date shootingDate = results.getDate("ShootingDate");
        int numberOfGuns = results.getInt("NumberOfGuns");
        String characteristics = results.getString("Characteristics");

        return new Shooting(shootingId, new CityDao().getCityFromCityId(cityId), shootingDate,
            numberOfGuns, characteristics);
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
    return null;
  }

  /**
   * Gets the number of shooting for the given city.
   *
   * @param city the city the user would like the number of shootings from
   * @return number of shootings that the database contains for the given city
   * @throws SQLException if communication with MySQL fails.
   */
  int getNumShootingsForCity(City city) throws SQLException {
    String selectShooting = "SELECT count(*) AS Count FROM Shootings WHERE CityId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectShooting);
      selectStmt.setInt(1, city.getCityId());
      results = selectStmt.executeQuery();
      if (results.next()) {
        return results.getInt("Count");

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
    return -1;
  }

  /**
   * Gets a list of the last 10 shootings from the given state.
   *
   * @param state the state that the user would like to see the last 10 shooting
   * @return list of the last 10 shootings from the given state
   * @throws SQLException if communication with MySQL fails.
   */
  public List<Shooting> getStatesLastTenShootings(State state) throws SQLException {
    List<Shooting> statesShootings = new ArrayList<Shooting>();
    String selectShootings =
        "SELECT *\n" +
            "FROM States\n" +
            "  INNER JOIN Cities\n" +
            "    ON States.StateAbbreviation = Cities.StateAbbreviation\n" +
            "  INNER JOIN Shootings\n" +
            "    ON Cities.CityId = Shootings.CityId\n" +
            "WHERE Cities.StateAbbreviation = ?\n" +
            "ORDER BY ShootingDate DESC\n" +
            "LIMIT 10";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectShootings);
      selectStmt.setString(1, state.getStateAbbreviation());
      results = selectStmt.executeQuery();

      while (results.next()) {
        int shootingId = results.getInt("ShootingId");
        int cityId = results.getInt("CityId");
        Date shootingDate = results.getDate("ShootingDate");
        int numberOfGuns = results.getInt("NumberOfGuns");
        String characteristics = results.getString("Characteristics");

        Shooting shooting = new Shooting(shootingId, new CityDao().getCityFromCityId(cityId),
            shootingDate, numberOfGuns, characteristics);

        statesShootings.add(shooting);
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
    return statesShootings;
  }

  /**
   * Deletes the shooting from the database.
   *
   * @param shooting the shooting to be deleted from the database
   * @return deleted shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public Shooting delete(Shooting shooting) throws SQLException {
    String deleteShooting = "DELETE FROM Shootings WHERE ShootingID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteShooting);
      deleteStmt.setInt(1, shooting.getShootingId());
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

  /**
   * Gets a list of shooting, that are in the database, for the given city.
   *
   * @param cityId the unique identifier for the city the user would like the shooting from
   * @return list of shootings, that are in the database, for the given city
   * @throws SQLException if communication with MySQL fails.
   */
  public List<Shooting> getShootingsByCityId(int cityId) throws SQLException {
    List<Shooting> shootings = new ArrayList<Shooting>();
    String selectShootings =
        "SELECT CityId, CityName, StateName, States.StateAbbreviation, ShootingId, Characteristics, NumberOfGuns, ShootingDate\n" +
            "FROM States\n" +
            "    INNER JOIN(\n" +
            "        SELECT Shootings.CityId, CityName, StateAbbreviation, ShootingId, Characteristics, NumberOfGuns, ShootingDate\n" +
            "            FROM Cities \n" +
            "                Inner join shootings on shootings.cityId = cities.CityId) AS shootingsPerCity\n" +
            "    On shootingsPerCity.StateAbbreviation = States.StateAbbreviation\n" +
            "where CityId =?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectShootings);
      selectStmt.setInt(1, cityId);
      results = selectStmt.executeQuery();

      while (results.next()) {
        String cityName = results.getString("CityName");
        String stateName = results.getString("StateName");
        String stateAbbreviation = results.getString("StateAbbreviation");
        int shootingId = results.getInt("ShootingId");
        Date shootingDate = results.getDate("ShootingDate");
        int numberOfGuns = results.getInt("NumberOfGuns");
        String characteristics = results.getString("Characteristics");

        State state = new State(stateAbbreviation, stateName);
        City city = new City(cityId, cityName, state);
        Shooting shooting = new Shooting(shootingId, city, shootingDate, numberOfGuns,
            characteristics);

        shootings.add(shooting);
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
    return shootings;
  }
}

