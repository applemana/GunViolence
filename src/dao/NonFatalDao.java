package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.City;
import model.NonFatal;
import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link NonFatal} into your MySQL instance and retrieve
 * {@link NonFatal} from MySQL instance.
 */
public class NonFatalDao {
  private ConnectionManager connectionManager;

  private static NonFatalDao instance = null;

  protected NonFatalDao() {
    connectionManager = new ConnectionManager();
  }

  public static NonFatalDao getInstance() {
    if (instance == null) {
      instance = new NonFatalDao();
    }
    return instance;
  }

  /**
   * Inserts a non-fatal shooting into the database.
   *
   * @param nonFatal the non-fatal shooting that will be added to the database.
   * @return newly created non-fatal shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public NonFatal create(NonFatal nonFatal) throws SQLException {
    String insertCity = "INSERT INTO NonFatal(ShootingId, NumberInjured) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, nonFatal.getShootingId());
      insertStmt.setInt(2, nonFatal.getNumberInjured());
      insertStmt.executeUpdate();

      return nonFatal;
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
   * Deletes the given non-fatal shooting from the database.
   *
   * @param nonFatal the non-fatal shooting to be deleted from the database
   * @return deleted non-fatal shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public NonFatal delete(NonFatal nonFatal) throws SQLException {
    String deleteNonFatal = "DELETE FROM NonFatal WHERE ShootingID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteNonFatal);
      deleteStmt.setInt(1, nonFatal.getShootingId());
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
