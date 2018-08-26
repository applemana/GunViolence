package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link State} into your MySQL instance and retrieve
 * {@link State} from MySQL instance.
 */
public class StateDao {
  private ConnectionManager connectionManager;

  private static StateDao instance = null;

  protected StateDao() {
    connectionManager = new ConnectionManager();
  }

  public static StateDao getInstance() {
    if (instance == null) {
      instance = new StateDao();
    }
    return instance;
  }

  /**
   * Inserts a state into the database.
   *
   * @param state the state to be added to the database
   * @return newly inserted state
   * @throws SQLException if communication with MySQL fails.
   */
  public State create(State state) throws SQLException {
    String insertCity = "INSERT INTO States(StateAbbreviation, StateName) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setString(1, state.getStateAbbreviation());
      insertStmt.setString(2, state.getStateName());

      insertStmt.executeUpdate();

      return state;
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
   * Gets the state from the database from its abbreviation.
   *
   * @param abbreviation the unique abbreviation for the state
   * @return state associated with the given abbreviation
   * @throws SQLException if communication with MySQL fails.
   */
  public State getStateFromAbbreviation(String abbreviation) throws SQLException {
    String selectCityCount = "SELECT * From States WHERE StateAbbreviation=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCityCount);
      selectStmt.setString(1, abbreviation);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String stateName = results.getString("StateName");

        return new State(abbreviation, stateName);
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
   * Deletes a state from the database.
   *
   * @param state the state to delete from the database
   * @return deleted state
   * @throws SQLException if communication with MySQL fails.
   */
  public State delete(State state) throws SQLException {
    String deleteState = "DELETE FROM States WHERE StateAbbreviation=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteState);
      deleteStmt.setString(1, state.getStateAbbreviation());
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
   * Get a list of all states in the database.
   *
   * @return list of all states in the database.
   * @throws SQLException if communication with MySQL fails.
   */
  public List<State> getAllStates() throws SQLException {
    List<State> allStates = new ArrayList<>();
    String selectStates = "SELECT * FROM States;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectStates);
      results = selectStmt.executeQuery();

      while (results.next()) {
        String stateAbbreviation = results.getString("StateAbbreviation");
        String stateName = results.getString("StateName");

        State state = new State(stateAbbreviation, stateName);

        allStates.add(state);
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
    return allStates;
  }
}
