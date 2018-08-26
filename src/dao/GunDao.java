package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Gun;
import model.Shooting;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link Gun} into your MySQL instance and retrieve
 * {@link Gun} from MySQL instance.
 */
public class GunDao {
  private ConnectionManager connectionManager;

  private static GunDao instance = null;

  protected GunDao() {
    connectionManager = new ConnectionManager();
  }

  public static GunDao getInstance() {
    if (instance == null) {
      instance = new GunDao();
    }
    return instance;
  }


  /**
   * Inserts a gun into the database given the gun.
   *
   * @param gun the gun to be inserted into the database
   * @return newly created gun
   * @throws SQLException if communication with MySQL fails.
   */
  public Gun create(Gun gun) throws SQLException {
    String insertCity = "INSERT INTO Guns(GunId, GunStolen, GunType, ShootingId, GunNumber) " +
        "VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity);
      insertStmt.setInt(1, gun.getGunId());
      insertStmt.setBoolean(2, gun.getStolen());
      insertStmt.setString(3, gun.getGunType());
      insertStmt.setInt(4, gun.getShooting().getShootingId());
      insertStmt.setInt(5, gun.getNumberOfGuns());
      insertStmt.executeUpdate();

      return gun;
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
   * Gets the number of guns used in the database for a particular shooting.
   *
   * @param shooting the shootings the user would like the know the number of guns for
   *
   * @return number of guns in the database for shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public int getNumberGunsUsedInShooting(Shooting shooting) throws SQLException {
    int numberOfGuns = 0;
    String selectGuns =
        "SELECT COUNT(*) as count FROM Guns WHERE ShootingId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectGuns);
      selectStmt.setInt(1, shooting.getShootingId());
      results = selectStmt.executeQuery();

      while (results.next()) {
        numberOfGuns = results.getInt("count");
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
    return numberOfGuns;
  }


  /**
   * Gets a list of all guns used in a shooting.
   *
   * @param shooting the shooting that you would like the guns from.
   * @return list of all guns used in the shooting
   * @throws SQLException if communication with MySQL fails.
   */
  public List<Gun> getGunsUsedInShooting(Shooting shooting) throws SQLException {
    List<Gun> gunsFromShooting = new ArrayList<Gun>();
    String selectGuns =
        "SELECT * FROM Guns WHERE ShootingId=?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectGuns);
      selectStmt.setInt(1, shooting.getShootingId());
      results = selectStmt.executeQuery();

      while (results.next()) {
        int gunId = results.getInt("GunId");
        Boolean gunStolen = results.getBoolean("GunStolen");
        String gunType = results.getString("GunType");
        int shootingId = results.getInt("ShootingId");
        int gunNumber = results.getInt("GunNumber");

        Gun gun = new Gun(gunId, gunStolen, gunType,
            new ShootingDao().getShootingFromShootingId(shootingId), gunNumber);

        gunsFromShooting.add(gun);
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
    return gunsFromShooting;
  }

  /**
   * Deletes the gun from the MySQL table.
   *
   * @param gun the gun to be deleted from the table
   * @return deleted gun
   * @throws SQLException if communication with MySQL fails.
   */
  public Gun delete(Gun gun) throws SQLException {
    String deleteGun = "DELETE FROM Guns WHERE GunID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteGun);
      deleteStmt.setInt(1, gun.getGunId());
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
