package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.City;
import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link City} into your MySQL instance and retrieve
 * {@link City} from MySQL instance.
 */
public class CityDao {
  private ConnectionManager connectionManager;

  private static CityDao instance = null;

  protected CityDao() {
    connectionManager = new ConnectionManager();
  }

  public static CityDao getInstance() {
    if (instance == null) {
      instance = new CityDao();
    }
    return instance;
  }

  /**
   * Save the City instance by storing it in your MySQL instance.
   * This runs an INSERT statement.
   *
   * @param city the city that will be added to the table
   * @return city that was added to the table
   * @throws SQLException if communication with MySQL fails.
   */
  public City create(City city) throws SQLException {
    String insertCity = "INSERT INTO Cities(CityId, CityName, StateAbbreviation) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, city.getCityId());
      insertStmt.setString(2, city.getCityName());
      insertStmt.setString(3, city.getState().getStateAbbreviation());

      insertStmt.executeUpdate();

      return city;
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
   * Get the City by fetching it from your MySQL instance.
   * This runs a SELECT statement and returns the city with the queried cityId.
   *
   * @param cityId the unique identifier used to search for a city.
   * @return city that has the cityId
   * @throws SQLException if communication with MySQL fails.
   */
  public City getCityFromCityId(Integer cityId) throws SQLException {
    String selectCityCount = "SELECT * FROM Cities WHERE cityId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCityCount);
      selectStmt.setInt(1, cityId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String cityName = results.getString("CityName");
        String stateAbbreviation = results.getString("StateAbbreviation");

        return new City(cityId, cityName, new StateDao().getStateFromAbbreviation(stateAbbreviation));
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
   * Creates a list of cities from a SQL lookup.
   *
   * @param selectStatement the SQL SELECT statement used to find the cities
   * @return list of cities that the selectStatement returns
   * @throws SQLException if communication with MySQL fails.
   */
  private List<City> cityListResults(String selectStatement) throws SQLException {
    List<City> listOfCites = new ArrayList<>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectStatement);
      results = selectStmt.executeQuery();

      while (results.next()) {
        int cityId = results.getInt("CityId");
        String cityName = results.getString("CityName");
        String stateAbbreviation = results.getString("StateAbbreviation");

        City city =
            new City(cityId, cityName, new StateDao().getStateFromAbbreviation(stateAbbreviation));
        listOfCites.add(city);

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
    return listOfCites;
  }

  /**
   * Gets a list of all cities within the cities SQL table.
   *
   * @return list of all cities
   * @throws SQLException if communication with MySQL fails.
   */
  List<City> getAllCities() throws SQLException {

    String selectCities = "SELECT * FROM Cities";

    return cityListResults(selectCities);
  }

  /**
   * Deletes city from the SQL table.
   *
   * @param city the city that will be removed from the SQL table
   * @return city that was deleted.
   * @throws SQLException if communication with MySQL fails.
   */
  public City delete(City city) throws SQLException {
    String deleteCity = "DELETE FROM Cities WHERE cityId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCity);
      deleteStmt.setInt(1, city.getCityId());
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
   * Gets a list of cities within the given state.
   *
   * @param state the state that you would like all of the cities from
   * @return list of cities that are in the given state
   * @throws SQLException if communication with MySQL fails.
   */
  public List<City> getCitiesByState(State state) throws SQLException {
    List<City> listOfCities = new ArrayList<>();
    String selectCities =
        "SELECT * FROM Cities WHERE StateAbbreviation=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCities);
      selectStmt.setString(1, state.getStateAbbreviation());
      results = selectStmt.executeQuery();

      while (results.next()) {
        int cityId = results.getInt("CityId");
        String cityName = results.getString("CityName");

        City city = new City(cityId, cityName, state);
        listOfCities.add(city);
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
    return listOfCities;
  }

  /**
   * Gets the state abbreviation for the city
   *
   * @param cityId the unique identifier for the city
   * @return state abbreviation where the city is located
   * @throws SQLException if communication with MySQL fails.
   */
  public String getStateAbbFromCityId(String cityId) throws SQLException {
    String selState = "SELECT StateAbbreviation FROM Cities WHERE CityId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    String stateAbb = "";

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selState);
      selectStmt.setString(1, cityId);
      results = selectStmt.executeQuery();

      while (results.next()) {
        stateAbb = results.getString("StateAbbreviation");
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
    return stateAbb;
  }
}
