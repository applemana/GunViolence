package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.City;
import model.Fatal;
import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link Fatal} into your MySQL instance and retrieve
 * {@link Fatal} from MySQL instance.
 */
public class FatalDao {
  private ConnectionManager connectionManager;

  private static FatalDao instance = null;

  private FatalDao() {
    connectionManager = new ConnectionManager();
  }

  public static FatalDao getInstance() {
    if (instance == null) {
      instance = new FatalDao();
    }
    return instance;
  }

  /**
   * Inserts a fatal shooting into the database.
   *
   * @param fatal the fatal shooting that will be added to the database
   * @return the newly created fatal shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public Fatal create(Fatal fatal) throws SQLException {
    String insertCity = "INSERT INTO Fatal(ShootingId, NumberKilled, NumberInjured) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, fatal.getShootingId());
      insertStmt.setInt(2, fatal.getNumberKilled());
      insertStmt.setInt(3, fatal.getNumberInjured());
      insertStmt.executeUpdate();

      return fatal;
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
   * Deletes the fatal shooting from the MySQL table.
   *
   * @param fatal the fatal shooting to be deleted
   * @return deleted shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public Fatal delete(Fatal fatal) throws SQLException {
    String deleteFatal = "DELETE FROM Fatal WHERE ShootingID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteFatal);
      deleteStmt.setInt(1, fatal.getShootingId());
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
