package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.City;
import model.CityRating;
import model.Rating;
import model.State;

/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your MySQL
 * instance. This is used to store {@link Rating} into your MySQL instance and retrieve
 * {@link Rating} from MySQL instance.
 */
public class RatingDao {
  private ConnectionManager connectionManager;

  private static RatingDao instance = null;

  private RatingDao() {
    connectionManager = new ConnectionManager();
  }

  public static RatingDao getInstance() {
    if (instance == null) {
      instance = new RatingDao();
    }
    return instance;
  }

  /**
   * Insert a rating into the database.
   *
   * @param rating the rating to be inserted into the database
   * @return newly created rating
   * @throws SQLException if communication with MySQL fails.
   */
  public Rating create(Rating rating) throws SQLException {
    String insertCity = "INSERT INTO Ratings(Grade, ReasonForGrade, CityId) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCity,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, rating.getGrade().name());
      insertStmt.setString(2, rating.getReasonForGrade());
      insertStmt.setInt(3, rating.getCity().getCityId());

      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int ratingId = -1;
      if (resultKey.next()) {
        ratingId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      rating.setRatingId(ratingId);
      return rating;
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
   * Gets the rating for the given city.
   *
   * @param city the city the user would like the rating for
   * @return rating for city
   * @throws SQLException if communication with MySQL fails.
   */
  public Rating getRatingForCity(City city) throws SQLException {
    String selectCityCount = "SELECT *\n" +
        "FROM States\n" +
        "  INNER JOIN(\n" +
        "      SELECT Ratings.CityId, CityName, StateAbbreviation, Grade, ReasonForGrade, RatingId\n" +
        "         FROM Cities\n" +
        "           INNER JOIN Ratings on Ratings.CityId = Cities.CityId) AS ratingsPerCity\n" +
        "  ON ratingsPerCity.StateAbbreviation = States.StateAbbreviation\n" +
        "WHERE ratingsPerCity.CityId = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCityCount);
      selectStmt.setInt(1, city.getCityId());
      results = selectStmt.executeQuery();
      if (results.next()) {
        int ratingId = results.getInt("RatingId");
        String grade = results.getString("Grade");
        String reasonForGrade = results.getString("ReasonForGrade");
        int cityId = results.getInt("CityId");
        String cityName = results.getString("CityName");
        String stateAbbreviation = results.getString("StateAbbreviation");
        String stateName = results.getString("StateName");

        State state = new State(stateAbbreviation, stateName);
        City cityFull = new City(cityId, cityName, state);
        grade = grade == null ? "F" : grade;
        Rating rating = new Rating();
        return new Rating(ratingId, Rating.Grade.valueOf(rating.getGradeFromString(grade)),
            reasonForGrade, cityFull);
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
   * Updates the rating with the new grade and reason for the grade.
   *
   * @param rating    the rating to be updated
   * @param newGrade  the grade that will replace the previous reason
   * @param newReason the reason for grade that will replace the previous reason
   * @return updated rating
   * @throws SQLException if communication with MySQL fails.
   */
  public Rating updateRating(Rating rating, Rating.Grade newGrade, String newReason) throws SQLException {
    String updateGrade = "UPDATE Ratings SET Grade=?, ReasonForGrade=? WHERE RatingId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateGrade);
      updateStmt.setString(1, newGrade.toString());
      updateStmt.setString(2, newReason);
      updateStmt.setInt(3, rating.getRatingId());
      updateStmt.executeUpdate();

      rating.setGrade(newGrade);
      rating.setReasonForGrade(newReason);
      return rating;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  /**
   * Deletes the rating from the database.
   *
   * @param rating the rating that will be deleted
   * @return deleted rating
   * @throws SQLException if communication with MySQL fails.
   */
  public Rating delete(Rating rating) throws SQLException {
    String deleteRating = "DELETE FROM Ratings WHERE RatingID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRating);
      deleteStmt.setInt(1, rating.getRatingId());
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
   * Gets the rating given the ratings unique identifier.
   *
   * @param ratingId the ratings unique identifier
   * @return rating associated with ratingId
   * @throws SQLException if communication with MySQL fails.
   */
  public Rating getRatingById(int ratingId) throws SQLException {
    String selectBlogPost =
        "SELECT RatingId, Grade, ReasonForGrade, CityId " +
            "FROM Ratings " +
            "WHERE RatingId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectBlogPost);
      selectStmt.setInt(1, ratingId);
      results = selectStmt.executeQuery();
      CityDao cityDao = CityDao.getInstance();
      if (results.next()) {
        int resultRatingId = results.getInt("RatingId");
        Rating.Grade grade = Rating.Grade.valueOf(results.getString("Grade"));
        String reason = results.getString("ReasonForGrade");
        int cityId = results.getInt("cityId");

        City city = cityDao.getCityFromCityId(cityId);
        Rating rating = new Rating(resultRatingId, grade, reason, city);
        return rating;
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
   * Get the matching rating records by fetching from your MySQL instance.
   * This runs a SELECT statement and returns a map of ratings per City in the State.
   */

  /**
   * Gets a map containing all of cities in a state. Key = cityId value = rating.
   *
   * @param state the state the user would like all of the cities and ratings for
   * @return map of cities and their rating within the given state
   * @throws SQLException if communication with MySQL fails.
   */
  public Map<Integer, CityRating> getRatingsPerState(State state) throws SQLException {
    Map<Integer, CityRating> citiesRatings = new LinkedHashMap<>();
    String selectStatesRatings =
        "SELECT States.StateAbbreviation, StateName, CitiesRatings.CityId, CityName, RatingId, Grade, ReasonForGrade \n" +
            "    FROM States\n" +
            "        INNER JOIN(\n" +
            "            SELECT Cities.CityId, CityName, StateAbbreviation, RatingId, Grade, ReasonForGrade\n" +
            "            FROM Cities\n" +
            "                INNER JOIN Ratings ON Cities.CityId = Ratings.CityId) AS CitiesRatings\n" +
            "            ON CitiesRatings.StateAbbreviation = States.StateAbbreviation\n" +
            "    Where States.StateAbbreviation = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectStatesRatings);
      selectStmt.setString(1, state.getStateAbbreviation());
      results = selectStmt.executeQuery();

      while (results.next()) {
        Integer cityId = results.getInt("CityId");
        String cityName = results.getString("CityName");
        int ratingId = results.getInt("RatingId");
        String reason = results.getString("ReasonForGrade");
        Rating.Grade grade = Rating.Grade.valueOf(results.getString("Grade"));
        String stateAbbreviation = results.getString("StateAbbreviation");
        String stateName = results.getString("StateName");

        State stateFull = new State(stateAbbreviation, stateName);
        City city = new City(cityId, cityName, stateFull);
        Rating rating = new Rating(ratingId, grade, reason, city);

        citiesRatings.put(cityId, new CityRating(city, rating));
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
    return citiesRatings;
  }


  /**
   * Used to calculate the rating using the number of shootings for a given city.
   *
   * @throws SQLException if communication with MySQL fails.
   */
  public void calculateRatings() throws SQLException {
    CityDao cityDao = new CityDao();
    ShootingDao shootingDao = new ShootingDao();
    RatingDao ratingDao = new RatingDao();
    Rating.Grade grade = Rating.Grade.F;
    String reason = "Initialize";

    List<City> allCities;

    allCities = cityDao.getAllCities();

    for (City city : allCities) {
      int numShootings = shootingDao.getNumShootingsForCity(city);
      if (numShootings < 10) {
        grade = Rating.Grade.A_PLUS;
        reason = "Less than 10 shootings have occurred in the city";
      } else if (numShootings < 20) {
        grade = Rating.Grade.A;
        reason = "Between 10 and 19 shootings have occurred in the city";
      } else if (numShootings < 30) {
        grade = Rating.Grade.A_MINUS;
        reason = "Between 20 and 29 shootings have occurred in the city";
      } else if (numShootings < 40) {
        grade = Rating.Grade.B_PLUS;
        reason = "Between 30 and 39 shootings have occurred in the city";
      } else if (numShootings < 50) {
        grade = Rating.Grade.B;
        reason = "Between 40 and 49 shootings have occurred in the city";
      } else if (numShootings < 60) {
        grade = Rating.Grade.B_MINUS;
        reason = "Between 50 and 59 shootings have occurred in the city";
      } else if (numShootings < 70) {
        grade = Rating.Grade.C_PLUS;
        reason = "Between 60 and 69 shootings have occurred in the city";
      } else if (numShootings < 80) {
        grade = Rating.Grade.C;
        reason = "Between 70 and 79 shootings have occurred in the city";
      } else if (numShootings < 90) {
        grade = Rating.Grade.C_MINUS;
        reason = "Between 80 and 89 shootings have occurred in the city";
      } else if (numShootings < 100) {
        grade = Rating.Grade.D_PLUS;
        reason = "Between 90 and 99 shootings have occurred in the city";
      } else if (numShootings < 110) {
        grade = Rating.Grade.D;
        reason = "Between 100 and 109 shootings have occurred in the city";
      } else if (numShootings < 120) {
        grade = Rating.Grade.D_MINUS;
        reason = "Between 110 and 119 shootings have occurred in the city";
      } else {
        grade = Rating.Grade.F;
        reason = "Greater than 119 shootings have occurred in the city";
      }
      System.out.println(city);
      Rating rating = ratingDao.getRatingForCity(city);
      ratingDao.updateRating(rating, grade, reason);
    }
  }
}
