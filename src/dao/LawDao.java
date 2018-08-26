package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Law;
import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link Law} into your MySQL instance and retrieve
 * {@link Law} from MySQL instance.
 */
public class LawDao {
  private ConnectionManager connectionManager;

  private static LawDao instance = null;

  protected LawDao() {
    connectionManager = new ConnectionManager();
  }

  public static LawDao getInstance() {
    if (instance == null) {
      instance = new LawDao();
    }
    return instance;
  }

  /**
   * Inserts a law into the MySQL table.
   *
   * @param law the law to be inserted
   * @return newly created law
   * @throws SQLException if communication with MySQL fails.
   */
  public Law create(Law law) throws SQLException {
    String insertCity = "INSERT INTO Laws(LawId, Effect, Contents, Category, Year, " +
        "StateAbbreviation) VALUES(?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, law.getLawId());
      insertStmt.setString(2, law.getEffect().toString());
      insertStmt.setString(3, law.getContents());
      insertStmt.setString(4, law.getCategory());
      insertStmt.setString(5, law.getYear().toString());
      insertStmt.setString(6, law.getState().getStateAbbreviation());
      insertStmt.executeUpdate();

      return law;
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
   * Gets a list of all gun laws for the given state.
   *
   * @param state the state you would like the gun laws from.
   * @return list of all gun laws for that state
   * @throws SQLException if communication with MySQL fails.
   */
  public List<Law> getLawsByState(State state) throws SQLException {
    List<Law> stateLaws = new ArrayList<>();
    String selectLaws =
        "SELECT *\n" +
            "  FROM States\n" +
            "    INNER JOIN Laws ON Laws.StateAbbreviation =  States.StateAbbreviation\n" +
            "WHERE Laws.StateAbbreviation=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectLaws);
      selectStmt.setString(1, state.getStateAbbreviation());
      results = selectStmt.executeQuery();

      while (results.next()) {
        int lawId = results.getInt("LawId");
        String effect = results.getString("Effect");
        String contents = results.getString("Contents");
        String category = results.getString("Category");
        Date year = results.getDate("Year");
        String stateAbbreviation = results.getString("StateAbbreviation");
        String stateName = results.getString("StateName");

        State newState = new State(stateAbbreviation, stateName);
        Law law = new Law(lawId, Law.Effect.valueOf(effect), contents, category, year,
            newState);

        stateLaws.add(law);
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
    return stateLaws;
  }

  /**
   * Gets a list of gun laws of the given type.
   *
   * @param type the type of law that the user is looking for
   * @return list of gun laws of type
   * @throws SQLException if communication with MySQL fails.
   */
  public List<Law> getLawsByType(String type) throws SQLException {
    List<Law> stateLaws = new ArrayList<Law>();
    String selectLaws =
        "SELECT * FROM States " +
            "Inner join Laws on Laws.StateAbbreviation = States.StateAbbreviation " +
            "where Effect = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectLaws);
      selectStmt.setString(1, type);
      results = selectStmt.executeQuery();

      while (results.next()) {
        int lawId = results.getInt("LawId");
        String effect = results.getString("Effect");
        String contents = results.getString("Contents");
        String category = results.getString("Category");
        Date year = results.getDate("Year");
        String stateAbbreviation = results.getString("StateAbbreviation");
        String stateName = results.getString("StateName");

        State newState = new State(stateAbbreviation, stateName);

        Law law = new Law(lawId, Law.Effect.valueOf(effect), contents, category, year, newState);

        stateLaws.add(law);
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
    return stateLaws;
  }


  /**
   * Deletes a gun law from the MySQL database.
   *
   * @param law the law to be deleted
   * @return deleted law
   * @throws SQLException if communication with MySQL fails.
   */
  public Law delete(Law law) throws SQLException {
    String deleteLaw = "DELETE FROM Laws WHERE LawID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteLaw);
      deleteStmt.setInt(1, law.getLawId());
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
